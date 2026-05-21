import { useNavigate } from 'react-router-dom';
import './Login.css';

export default function Login() {
  const navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();
  }

  return (
    <div style={{width: '100%'}}>
      <div className="login-container">
        <h2>Login</h2>
        <form className="login-form" onSubmit={handleLogin}>
          <input type="email" placeholder="E-mail" />
          <input type="password" placeholder="Senha" />
          <button type="submit">Entrar</button>
        </form>
        <p>Não tem conta?</p>
        <button className="btn-criar" onClick={() => navigate('/criar-cliente')}>
          Criar conta
        </button>
      </div>
    </div>
  );
}