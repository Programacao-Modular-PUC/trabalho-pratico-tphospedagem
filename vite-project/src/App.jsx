import { BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom'
import Home from './assets/components/pages/Home.jsx'
import Residencias from './assets/components/pages/Residencias.jsx'
import Quartos from './assets/components/pages/Quartos.jsx'
import Cliente from './assets/components/pages/Cliente.jsx'
import Reserva from './assets/components/pages/Reserva.jsx'

import Container from './assets/components/layout/Container.jsx'
function App() {
  

  return (
    <>
      
    <Router>
      <div>
        <Link to="/">Home</Link>
        <Link to="/residencias">Residencias</Link>
        <Link to="/quartos">Quartos</Link>
        <Link to="/clientes">Cliente</Link>
        <Link to="/reserva">Reserva</Link>

      </div>
    
    <Container customClass="min-height">
      <Routes>
      
        <Route path="/" element={<Home/>} />
        <Route path="/residencias" element={<Residencias />} />
        <Route path="/quartos" element={<Quartos />} />
        <Route path="/clientes" element={<Cliente />} />
        <Route path="/reserva" element={<Reserva />} />
    </Routes>

  </Container>

  
  <p>Footer</p>
    </Router>

      

    </>
  )
}

export default App
