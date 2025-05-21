
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../css/Registrarse.css';
import logo from '../assets/beijian2.0.png';

function Registro() {
  // Estados de formulario
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const navigate = useNavigate();

  // Constantes adicionales
  const rol = 'Cliente';
  const nombre = ''; // No se está capturando por ahora, pero el backend lo espera

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage('');
    setSuccessMessage('');

    try {
      const response = await axios.post('http://localhost:8092/api/auth/registrar', {
        nombre,
        username,
        password,
        email,
        rol,
      });

      if (response.data.authstatus === 'USER_CREATED_SUCCESSFULLY') {
        setSuccessMessage('¡Registro exitoso! Serás redirigido al login...');
        setTimeout(() => {
          navigate('/login');
        }, 3000);
      } else {
        setErrorMessage('No se pudo crear el usuario. Intenta nuevamente.');
      }
    } catch (error) {
      if (error.response?.data?.authstatus === 'USER_NOT_CREATED') {
        setErrorMessage('Error: El usuario o el correo ya existen.');
      } else {
        setErrorMessage('Ocurrió un error inesperado. Intenta más tarde.');
      }
      console.error('Error de registro:', error);
    }
  };

  return (
    <div className="containerPersonalizado">
      <img src={logo} alt="Logo" className="logoPersonalizadoRegistro" />
      <span className="SpanBeijian textSpan registroSpan">Beijian</span>&nbsp;
      <span className="SpanPartes textSpan registroSpan">Registro</span>

      <form className="row g-3 formPersonalizado" onSubmit={handleSubmit}>
        {/* Mensajes de alerta */}
        {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
        {successMessage && <div className="alert alert-success">{successMessage}</div>}

        {/* Input: Email */}
        <div className="input-group mb-3">
          <input
            type="email"
            className="form-control"
            placeholder="Correo electrónico"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <span className="input-group-text">@example.com</span>
        </div>

        {/* Input: Contraseña */}
        <div className="input-group mb-3">
          <input
            type="password"
            className="form-control"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <span className="input-group-text">****</span>
        </div>

        {/* Input: Username */}
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Nombre de usuario"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <span className="input-group-text">@user</span>
        </div>

        {/* Botón de envío */}
        <div className="col-12">
          <button
            type="submit"
            className="btn btn-outline-warning btn-lg buttonRegistro"
          >
            Registrarse
          </button>
        </div>
      </form>
    </div>
  );
}

export default Registro;

