import { useState } from 'react';
import styles from "./Navbar.module.css"
import {Link, useNavigate} from 'react-router-dom'
import Container from "./Container"
import { useAuth } from '../../context/AuthContext.jsx'

function Logo() {
    return (
        <Link to="/" className={styles.brand}>
            <svg
                className={styles.brandIcon}
                width="34"
                height="34"
                viewBox="0 0 48 48"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
            >
                <path
                    d="M24 5 L44 22 H38 V41 H10 V22 H4 Z"
                    fill="#ffb020"
                />
                <rect x="20" y="27" width="8" height="14" rx="1" fill="#1e1e22" />
                <rect x="15" y="24" width="6" height="6" rx="1" fill="#1e1e22" />
                <rect x="27" y="24" width="6" height="6" rx="1" fill="#1e1e22" />
            </svg>
            <span className={styles.brandText}>Estadia</span>
        </Link>
    )
}

function Navbar(){
    const { cliente, logout } = useAuth();
    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    function handleLogout() {
        setMenuOpen(false);
        logout();
        navigate('/');
    }

    const primeiroNome = cliente?.nome?.split(' ')[0] ?? '';
    const inicial = cliente?.nome?.charAt(0)?.toUpperCase() ?? '?';

    return(
        <nav className={styles.navbar}>
        <Container>
        <Logo />

        <ul className={styles.list}>
            <li className={styles.item}>
                <Link to="/">Home</Link>
            </li>
            <li className={styles.item}>
                <Link to="/residencias">Residencias</Link>
            </li>
            <li className={styles.item}>
                <Link to="/quartos">Quartos</Link>
            </li>
            <li className={styles.item}>
                <Link to="/reserva">Reserva</Link>
            </li>
        </ul>

        <div className={styles.userArea}>
            {cliente ? (
                <div className={styles.userMenu}>
                    <button
                        type="button"
                        className={styles.userButton}
                        onClick={() => setMenuOpen((open) => !open)}
                    >
                        <span className={styles.avatar}>{inicial}</span>
                        <span className={styles.userName}>{primeiroNome}</span>
                    </button>
                    {menuOpen && (
                        <div className={styles.dropdown}>
                            <span className={styles.dropdownEmail}>{cliente.email}</span>
                            <Link to="/clientes" className={styles.dropdownLink} onClick={() => setMenuOpen(false)}>
                                Minhas reservas
                            </Link>
                            <button type="button" className={styles.logoutButton} onClick={handleLogout}>
                                Sair
                            </button>
                        </div>
                    )}
                </div>
            ) : (
                <Link to="/login" className={styles.loginButton}>Entrar</Link>
            )}
        </div>

        </Container>

    </nav>
    )
}

export default Navbar
