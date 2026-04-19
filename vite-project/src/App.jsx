import { BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom'
import Home from './assets/components/pages/Home.jsx'
import Residencias from './assets/components/pages/Residencias.jsx'
import Quartos from './assets/components/pages/Quartos.jsx'
import Cliente from './assets/components/pages/Cliente.jsx'
import Reserva from './assets/components/pages/Reserva.jsx'
import Login from './assets/components/pages/Login.jsx'
import CriarCliente from './assets/components/pages/CriarCliente.jsx'

import Container from './assets/components/layout/Container.jsx'
import Navbar from './assets/components/layout/Navbar.jsx'
import Footer from './assets/components/layout/Footer.jsx'
function App() {
  

  return (
    <>
      
    <Router>
      
      <Navbar />
    
    <Container customClass="min_height">
      <Routes>
      
        <Route path="/" element={<Home/>} />
        <Route path="/residencias" element={<Residencias />} />
        <Route path="/quartos" element={<Quartos />} />
        <Route path="/clientes" element={<Cliente />} />
        <Route path="/reserva" element={<Reserva />} />
        <Route path="/quartos/:id" element={<Quartos />} />
        <Route path="/login" element={<Login />} />
        <Route path="/criar-cliente" element={<CriarCliente />} />
    </Routes>

  </Container>

  
  <Footer />
    </Router>

      

    </>
  )
}

export default App
