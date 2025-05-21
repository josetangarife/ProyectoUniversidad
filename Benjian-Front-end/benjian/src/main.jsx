
import { createRoot } from 'react-dom/client'
import '../src/css/index.css'
import Registrar from '../src/componentes/Registrarse'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import About from'../src/Vistas/About'
import Home from'../src/Vistas/Home'
import CustomNavbar from'../src/componentes/NavBar'
import Login from'../src/componentes/Login'
import HomeUser from'../src/Vistas/HomeUser'
import { AuthProvider } from './componentes/AuthContext';




createRoot(document.getElementById('root')).render(
     
  
        <BrowserRouter>
    <AuthProvider>
      <CustomNavbar />
      <Routes>
        <Route path="/homeUser" element={<HomeUser />} />
        <Route path="/home" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/registrar" element={<Registrar />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </AuthProvider>
  </BrowserRouter>


)
