import styles from "./Navbar.module.css"
import {Link} from 'react-router-dom'
import Container from "./Container"
import logo from '../img/logohospedagem.png'

function Navbar(){
    return(
        <nav>
        <Container>
        <Link to="/">
            <img src={logo} alt="hospedagem" />
        </Link>

        <ul>
            <li>
                <Link to="/">Home</Link>
        
            </li>
            <li>
                <Link to="/residencias">Residencias</Link>
            </li>
            <li>
                <Link to="/quartos">Quartos</Link>
            </li>
            <li>
                <Link to="/clientes">Cliente</Link>
            </li>
            <li>
                <Link to="/reserva">Reserva</Link>
            </li>
        </ul>

      
        </Container>

    </nav>
    )
}

export default Navbar