import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './pages/Home.jsx'
import Residencias from './pages/Residencias.jsx'
import Quartos from './pages/Quartos.jsx'
import Cliente from './pages/Cliente.jsx'
import Reserva from './pages/Reserva.jsx'
import Login from './pages/Login.jsx'
import CriarCliente from './pages/CriarCliente.jsx'
import Pagamento from './pages/Pagamento.jsx'

import Container from './components/layout/Container.jsx'
import Navbar from './components/layout/Navbar.jsx'
import Footer from './components/layout/Footer.jsx'
import { AuthProvider } from './context/AuthContext.jsx'
function App() {


  return (
    <AuthProvider>
    <div className="app-shell">
    <Router>

      <Navbar />

    <Container customClass="min_height">
      <Routes>

        <Route path="/" element={<Home/>} />
        <Route path="/residencias" element={<Residencias />} />
        <Route path="/quartos" element={<Quartos />} />
        <Route path="/clientes" element={<Cliente />} />
        <Route path="/reserva" element={<Reserva />} />
        <Route path="/aluguel" element={<Reserva />} />
        <Route path="/quartos/:id" element={<Quartos />} />
        <Route path="/login" element={<Login />} />
        <Route path="/criar-cliente" element={<CriarCliente />} />
        <Route path="/pagamento" element={<Pagamento />} />
    </Routes>

  </Container>


  <Footer />
    </Router>
    </div>
    </AuthProvider>
  )
}

export default App