import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import './CriarCliente.css';

const initialState = {
  nome: '',
  cpf: '',
  telefone: '',
  email: '',
  senha: '',
  confirmarSenha: '',
  cep: '',
  cidade: '',
  estado: '',
};

export default function CriarCliente() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState(initialState);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError('');

    if (form.senha !== form.confirmarSenha) {
      setError('As senhas não coincidem.');
      return;
    }

    const endereco = [form.cep, form.cidade, form.estado].filter(Boolean).join(' - ');

    setLoading(true);
    try {
      const response = await fetch('/api/clientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nome: form.nome,
          cpf: form.cpf,
          telefone: form.telefone,
          email: form.email,
          senha: form.senha,
          endereco,
        }),
      });

      if (!response.ok) {
        const data = await response.json().catch(() => null);
        throw new Error(data?.message || 'Não foi possível criar a conta.');
      }

      const cliente = await response.json();
      login(cliente);
      navigate('/');
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={{ width: '100%' }}>
      <div className="criar-container">
        <h2>Criar Conta</h2>
        <form className="criar-form" onSubmit={handleSubmit}>

          <h3>Dados Pessoais</h3>
          <input type="text" name="nome" placeholder="Nome completo" value={form.nome} onChange={handleChange} required />
          <input type="text" name="cpf" placeholder="CPF" value={form.cpf} onChange={handleChange} required />
          <input type="tel" name="telefone" placeholder="Telefone" value={form.telefone} onChange={handleChange} required />

          <h3>Acesso</h3>
          <input type="email" name="email" placeholder="E-mail" value={form.email} onChange={handleChange} required />
          <input type="password" name="senha" placeholder="Senha" value={form.senha} onChange={handleChange} minLength={6} required />
          <input type="password" name="confirmarSenha" placeholder="Confirmar senha" value={form.confirmarSenha} onChange={handleChange} minLength={6} required />

          <h3>Endereço</h3>
          <input type="text" name="cep" placeholder="CEP" value={form.cep} onChange={handleChange} />
          <input type="text" name="cidade" placeholder="Cidade" value={form.cidade} onChange={handleChange} />
          <input type="text" name="estado" placeholder="Estado" value={form.estado} onChange={handleChange} />

          {error && <p className="form-error">{error}</p>}

          <button type="submit" disabled={loading}>
            {loading ? 'Criando conta...' : 'Cadastrar'}
          </button>
        </form>
      </div>
    </div>
  );
}
