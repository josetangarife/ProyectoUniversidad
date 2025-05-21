package com.projectu.proyecto_u.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectu.proyecto_u.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController //Spring reconoce esta anotacion y sabe que recibira las peticiones HTTP entrantes y dara un response de tipo JSON
@RequestMapping("/api/auth/") //TODAS las rutas de este controlador iniciaran por ("/api/auth")
@RequiredArgsConstructor// una anotacion de la libreria lombock que crea un constructor con parametos de esta clase
public class AuthController {

    @Autowired//anotacion que nos simplifica la inyeccion de dependencias a otra clase
    private AuthServiceImpl authService;//se inyecta las dependencias de AuthService a authservice que es una instancia que tiene el servicio de la logica para el login y del registro

    @PostMapping("/login")// maneja las solicitudes post de  ("/api/auth/login")

    //@ResponseBody spring convierte automaticamente el json del cuerpo en un objetoDto
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        var jwtToken = authService.Login(authRequestDto.username(), authRequestDto.password()); //llama un servicio Login que le pasan como parametro un (username, password) si es valido genera un token JWT

        var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);// SE CREA un dto con el token y el estado de respuesta como exito
        return ResponseEntity.status(HttpStatus.OK) //Devuelve un ResponseEntity con codigo HTTP 200 de exito 
                .body(authResponseDto);// y el objeto de respuesta tambien lo devuelve
    }

    
    @PostMapping("/registrar")//maneja las solicitudes POST hacia ("/api/auth/registrar").
public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto) {
      System.out.println("📥 DTO recibido: " + authRequestDto);//imprime en consola el DTO recibido
    try {
        String jwtToken = authService.SignUp(
            authRequestDto.nombre(),
            authRequestDto.username(),
            authRequestDto.password(),
            authRequestDto.email(),
            authRequestDto.rol()
        ); // intenta registrar un nuevo usuario llamando a singuP
    

        var response = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);

        return ResponseEntity.status(HttpStatus.OK).body(response);// devuelve un estado una respuesta HTTP con estado 200 ok y el token

    } catch (RuntimeException e) { //maneja los errores al usuario no ser creado y devuelve una respuesta, si el usuario es existente devuelve un 409 CONFLICT 
        var response = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

}

/*GRAFICO DE LA CALSE AuthController: 
 * 
 * 
                                             ┌────────────────────────────────────────────┐
                                             │ Cliente (Frontend / Postman / App móvil)   │
                                             └────────────────────────────────────────────┘
                                                            │
                                                POST /api/auth/login   o   /api/auth/registrar
                                                            │
                                                            ▼
                                            ┌───────────────────────────────────────────┐
                                            │               AuthController              │
                                            └───────────────────────────────────────────┘
                                             │                                         │
                                             ▼                                         ▼
                                       authService.Login(...)                authService.SignUp(...)
                                             │                                         │
                                             ▼                                         ▼
                                         genera JWT                              genera usuario y JWT
                                             │                                         │
                                             ▼                                         ▼
                                      ResponseEntity<AuthResponseDto>          ResponseEntity<AuthResponseDto>
                                             │                                         │
                                             ▼                                         ▼
                                      HTTP 200 OK + token                      HTTP 200 OK o 409 CONFLICT

 * 
 * 
 * 
 */
