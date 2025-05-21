/*import React, { useState } from 'react'
import logo from'../assets/beijian2.0.png'
import { useNavigate } from 'react-router-dom';
import {useAuth} from'../componentes/AuthContext'

function Login()  {

    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");
   const [error, setError] = useState("");
const [successMessage, setSuccessMessage] = useState("");
    const navigate = useNavigate();
    const {login} = useAuth();

    const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage('');
    setSuccessMessage('');

   try {
      // Envía una solicitud POST al backend con los datos del formulario
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        {
          username: username, // Envía el nombre de usuario ingresado
          password: password, // Envía la contraseña ingresada
          role : "Cliente"
        }
      );

      // Verifica si el inicio de sesión fue exitoso
      if (response.data.authStatus === "LOGIN_SUCCESS") {
        setSuccessMessage("Inicio de sesión exitoso"); // Muestra el mensaje de éxito
        // Guarda el token JWT en el almacenamiento local para futuras solicitudes
        localStorage.setItem("token", response.data.token);

        login({
          username: username,
          name: username,
        });

        // Espera 1 segundo antes de redirigir al usuario a la página principal
        setTimeout(() => {
          navigate("/homeUser"); // Redirige a la ruta home
        }, 2000);
      }
    } catch (err) {

      setSuccessMessage("");
      // Maneja errores y muestra mensajes apropiados
      if (err.response?.data?.message) {
        setError(err.response.data.message); // Muestra el mensaje de error del backend
      } else {
        setError("Error al iniciar sesión. Por favor, intente más tarde."); // Mensaje genérico de error
      }
      console.error("Error detallado:", err); // Imprime el error en la consola para depuración
    }
  };




  return (
    <div className="containerPersonalizado">
            <img src={logo} alt="Logo" className="logoPersonalizadoRegistro" />

            <span className="SpanBeijian textSpan registroSpan">Beijian</span>&nbsp;
            <span className="SpanPartes textSpan registroSpan">Login</span>&nbsp;&nbsp;&nbsp;

                <form className="row g-3 formPersonalizado" onSubmit={handleSubmit}>
                     {error && !successMessage && (
                       <div className="alert alert-danger" role="alert">
                          {error}
                       </div>
                     )}
                      {successMessage && !error && (
                     <div className="alert alert-success" role="alert">
                       {successMessage}
                     </div>
                     )}

                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="your-UserName"
                            aria-label="Username"
                            aria-describedby="basic-addon1"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                        <span className="input-group-text" id="basic-addon2">@user</span>
                    </div>
                    <div className="input-group mb-3">
                        <input
                            type="password"
                            className="form-control"
                            placeholder="your-Password"
                            aria-label="ingresa tu Contraseña"
                            aria-describedby="basic-addon2"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                          <span className="input-group-text" id="basic-addon2">" **** "</span>
                    </div>
                    <div className="col-12">
                        <button
                            type="submit"
                            className="btn btn-outline-warning btn-lg buttonRegistro tn-outline-light me-2"
                        >
                            Iniciar Session
                        </button>
                    </div>
                </form>
    </div>
        )
}

export default Login;*/
import React, { useState } from 'react';
import logo from '../assets/beijian2.0.png';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../componentes/AuthContext';
import axios from 'axios';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccessMessage('');

    try {
      const response = await axios.post('http://localhost:8092/api/auth/login', {
        username: username,
        password: password,
       
      });

      if (response.data.authstatus === 'LOGIN_SUCCESS' ) {
        setSuccessMessage('Inicio de sesión exitoso');
        localStorage.setItem('token', response.data.token);

        login({
          username: username,
          name: username,
        });

        setTimeout(() => {
          navigate('/homeUser');
        }, 2000);
      } else {
        setError('Credenciales incorrectas.');
      }
    } catch (err) {
      setSuccessMessage('');
      if (err.response?.data?.message) {
        setError(err.response.data.message);
      } else {
        setError('Error al iniciar sesión. Por favor, intente más tarde.');
      }
      console.error('Error detallado:', err);
    }
  };

  return (
    <div className="containerPersonalizado">
      <img src={logo} alt="Logo" className="logoPersonalizadoRegistro" />

      <span className="SpanBeijian textSpan registroSpan">Beijian</span>&nbsp;
      <span className="SpanPartes textSpan registroSpan">Login</span>&nbsp;&nbsp;&nbsp;

      <form className="row g-3 formPersonalizado" onSubmit={handleSubmit}>
        {error && !successMessage && (
          <div className="alert alert-danger" role="alert">
            {error}
          </div>
        )}
        {successMessage && !error && (
          <div className="alert alert-success" role="alert">
            {successMessage}
          </div>
        )}

        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="your-UserName"
            aria-label="Username"
            aria-describedby="basic-addon1"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <span className="input-group-text" id="basic-addon2">@user</span>
        </div>

        <div className="input-group mb-3">
          <input
            type="password"
            className="form-control"
            placeholder="your-Password"
            aria-label="ingresa tu Contraseña"
            aria-describedby="basic-addon2"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <span className="input-group-text" id="basic-addon2">****</span>
        </div>

        <div className="col-12">
          <button
            type="submit"
            className="btn btn-outline-warning btn-lg buttonRegistro tn-outline-light me-2"
          >
            Iniciar Sesión
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;

