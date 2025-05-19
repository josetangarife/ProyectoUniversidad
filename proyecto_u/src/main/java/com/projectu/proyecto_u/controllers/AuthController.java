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

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        var jwtToken = authService.Login(authRequestDto.username(), authRequestDto.password());

        var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authResponseDto);
    }

    /*@PostMapping("/registrar")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto) {
        try {
            var jwtToken = authService.SignUp(authRequestDto.nombre(), authRequestDto.username(),
                    authRequestDto.password(), authRequestDto.email());

            var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(authResponseDto);
        } catch (Exception e) {

            e.printStackTrace();

            var authResponseDto = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        }
    }*/
    @PostMapping("/registrar")
public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto) {
      System.out.println("📥 DTO recibido: " + authRequestDto);
    try {
        String jwtToken = authService.SignUp(
            authRequestDto.nombre(),
            authRequestDto.username(),
            authRequestDto.password(),
            authRequestDto.email(),
            authRequestDto.rol()
        );
    

        var response = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    } catch (RuntimeException e) {
        var response = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

}
