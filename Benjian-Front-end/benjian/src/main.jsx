
import { createRoot } from 'react-dom/client'
import '../src/css/index.css'
import Registrarse from './Vistas/Registrarse';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import About from'../src/Vistas/About'
import Home from'../src/Vistas/Home'
import CustomNavbar from'../src/componentes/NavBar'
import Login from './Vistas/Login';




createRoot(document.getElementById('root')).render(
     
  
       <BrowserRouter>
         <CustomNavbar/>
      <Routes>
           <Route path="/home" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path='/registrarse' element={<Registrarse/>} />
        <Route path='/login' element={<Login/>}/>

      
      </Routes>
    </BrowserRouter>


)
