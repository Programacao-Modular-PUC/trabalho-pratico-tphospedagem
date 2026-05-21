import styles from "./Navbar.module.css"
import {Link} from 'react-router-dom'
import Container from "./Container"
import logo from '../img/logohospedagem.png'

function Navbar(){
    return(
        <nav className={styles.navbar}>
        <Container>
        <Link to="/">
            <img src={logo} alt="hospedagem" />
        </Link>

        <ul className={styles.list}>
            <li className={styles.item}>
                <Link to="/">Home</Link>
        
            </li>
            <li className={styles.item}>
                <Link to="/residencias">Residencias</Link>
            </li>
            <li className={styles.item}>
                <Link to="/login">Cliente</Link>
            </li>
            <li className={styles.item}>
                <Link to="/reserva">Reserva</Link>
            </li>
            
        </ul>

      
        </Container>

    </nav>
    )
}

export default Navbar