import React from 'react'
import moto1 from '../assets/moto1.jpg'
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { useEffect } from 'react';



 const Home = () => {
  useEffect(() => {
    const el = document.querySelector('#carouselHome');
    if (el && window.bootstrap?.Carousel) {
      new window.bootstrap.Carousel(el, {
        interval: 3000,
        ride: 'carousel',
        pause: false,
      });
    }
  }, []);
  
  return (
    <div>
      <div id="carouselHome" className="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="3000">
        {/* Indicadores opcionales */}
        <div className="carousel-indicators">
          <button type="button" data-bs-target="#carouselHome" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselHome" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselHome" data-bs-slide-to="2" aria-label="Slide 3"></button>
          <button type="button" data-bs-target="#carouselHome" data-bs-slide-to="3" aria-label="Slide 4"></button>
        </div>

        <div className="carousel-inner">
          <div className="carousel-item active">
            <img src={moto1} className="d-block w-100" alt="Moto 1" style={{ height: '400px', objectFit: 'cover' }} />
          </div>
          <div className="carousel-item">
            <div className="d-block w-100 bg-dark text-white text-center" style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
              <h1 className="display-4">Beijian</h1>
            </div>
          </div>
          <div className="carousel-item">
            <div className="d-block w-100 bg-secondary text-white text-center" style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
              <h1 className="display-4">Aplicativo unificador de talleres de motos</h1>
            </div>
          </div>
          <div className="carousel-item">
            <div className="d-block w-100 bg-primary text-white text-center" style={{ height: '400px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
              <h1 className="display-4">Encuentra tus repuestos más rápido</h1>
            </div>
          </div>
        </div>
      </div>
      {/* Contenido principal */}
      <div className="container py-5">
        <div className="text-center mb-4">
          <h2>Misión</h2>
          <p className="lead">Brindar una solución integral que conecte talleres de motos en una sola plataforma.</p>
        </div>

        <div className="text-center mb-4">
          <h2>Visión</h2>
          <p className="lead">Ser la plataforma líder en Colombia en servicios y repuestos para motocicletas.</p>
        </div>

        <div className="text-center mb-4">
          <h2>Objetivo</h2>
          <p className="lead">Llegar a todos los colombianos con la intención de ahorrarles tiempo al buscar repuestos automotrices.</p>
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-dark text-white text-center py-3">
        <p className="mb-0">&copy; {new Date().getFullYear()} Beijian. Todos los derechos reservados.</p>
      </footer>
    </div>
  )
};
 export default Home;
