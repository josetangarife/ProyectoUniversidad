import React from 'react'
import logo from'../assets/beijian2.0.png'

function Login()  {
  return (
    <div className="containerPersonalizado">
            <img src={logo} alt="Logo" className="logoPersonalizadoRegistro" />

            <span className="SpanBeijian textSpan registroSpan">Beijian</span>&nbsp;
            <span className="SpanPartes textSpan registroSpan">Login</span>&nbsp;&nbsp;&nbsp;

                <form className="row g-3 formPersonalizado">
            
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="your-UserName"
                            aria-label="Username"
                            aria-describedby="basic-addon1"
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

export default Login;
