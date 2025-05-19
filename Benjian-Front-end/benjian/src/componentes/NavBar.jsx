
import { useNavigate } from 'react-router-dom'; 
import logo from '../assets/beijian2.0.png';
import '../css/navBar.css';

const NavBar = () => {

  const navigate = useNavigate(); 

  return (
    <nav className="navbar navbar-expand-lg bg-dark navbar-dark w-100">
      <div className="container-fluid">
        <a className="navbar-brand" href="#">
          <img 
            src={logo} 
            alt="Logo" 
            width="100" 
            height="65" 
            className="d-inline-block align-text-top" 
          />
        </a>
        <span className='SpanBeijian textSpan'>Beijian</span>&nbsp;
        <span className='SpanPartes textSpan'>Partes</span>&nbsp;&nbsp;&nbsp;

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link" href="/home">Home Page</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/about">Sobre Nosotros</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#" aria-disabled="true">Contactanos</a>
            </li>
          </ul>
        </div>

        {/* Botones alineados al final del navbar */}
        <div className="d-flex ms-auto">
          <button className="btn btn-success" onClick={() => navigate('/registrarse')}>Registrarse</button>&nbsp;&nbsp;
          <button className="btn btn-outline-light me-2" onClick={()=> navigate('/login')}>Iniciar Sesión</button>&nbsp;&nbsp;
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
