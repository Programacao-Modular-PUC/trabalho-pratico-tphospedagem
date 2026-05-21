import './CriarCliente.css';

export default function CriarCliente() {
  function handleSubmit(e) {
    e.preventDefault();
  }

  return (
    <div style={{width: '100%'}}>
      <div className="criar-container">
        <h2>Criar Conta</h2>
        <form className="criar-form" onSubmit={handleSubmit}>

          <h3>Dados Pessoais</h3>
          <input type="text" placeholder="Nome completo" />
          <input type="text" placeholder="CPF" />
          <input type="date" placeholder="Data de nascimento" />
          <input type="tel" placeholder="Telefone" />

          <h3>Acesso</h3>
          <input type="email" placeholder="E-mail" />
          <input type="password" placeholder="Senha" />
          <input type="password" placeholder="Confirmar senha" />

          <h3>Endereço</h3>
          <input type="text" placeholder="CEP" />
          <input type="text" placeholder="Cidade" />
          <input type="text" placeholder="Estado" />

          <button type="submit">Cadastrar</button>
        </form>
      </div>
    </div>
  );
}