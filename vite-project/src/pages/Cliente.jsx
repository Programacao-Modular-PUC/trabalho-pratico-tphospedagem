import { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import './Cliente.css';

const STATUS_LABEL = {
  ATIVO: 'Ativo',
  CANCELADO: 'Cancelado',
  FINALIZADO: 'Finalizado',
};

export default function Cliente() {
  const { cliente } = useAuth();
  const navigate = useNavigate();
  const [alugueis, setAlugueis] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [cancelandoId, setCancelandoId] = useState(null);
  const [cancelError, setCancelError] = useState('');
  const [excluindoId, setExcluindoId] = useState(null);
  const [excluirError, setExcluirError] = useState('');
  const [notificacoes, setNotificacoes] = useState([]);

  async function carregarNotificacoes(clienteId) {
    try {
      const response = await fetch(`/api/notificacoes/${clienteId}`);
      if (!response.ok) return;
      setNotificacoes(await response.json());
    } catch {
      // histórico de notificações é informativo; falha aqui não bloqueia a página
    }
  }

  async function carregarReservas(clienteId) {
    try {
      setLoading(true);
      setError('');
      const response = await fetch(`/api/alugueis?clienteId=${clienteId}`);
      if (!response.ok) throw new Error('Não foi possível carregar suas reservas.');
      setAlugueis(await response.json());
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    if (!cliente) {
      setLoading(false);
      return;
    }

    carregarReservas(cliente.id);
    carregarNotificacoes(cliente.id);
  }, [cliente]);

  async function handleCancelar(aluguelId) {
    setCancelandoId(aluguelId);
    setCancelError('');

    try {
      const response = await fetch(`/api/alugueis/${aluguelId}/cancelar`, {
        method: 'PUT',
      });

      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.message || 'Não foi possível cancelar a reserva.');
      }

      setAlugueis((atual) => atual.map((a) => (a.id === aluguelId ? data : a)));
      // a Funcionalidade 3 (Strategy de canais) dispara a notificação no backend
      // ao cancelar; recarregamos o histórico pra refletir a nova mensagem aqui
      await carregarNotificacoes(cliente.id);
    } catch (err) {
      setCancelError(err.message);
      await carregarReservas(cliente.id);
    } finally {
      setCancelandoId(null);
    }
  }

  async function handleExcluir(aluguelId) {
    setExcluindoId(aluguelId);
    setExcluirError('');

    try {
      const response = await fetch(`/api/alugueis/${aluguelId}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        const data = await response.json().catch(() => null);
        throw new Error(data?.message || 'Não foi possível excluir a reserva.');
      }

      setAlugueis((atual) => atual.filter((a) => a.id !== aluguelId));
    } catch (err) {
      setExcluirError(err.message);
      // o bloqueio pode significar que a reserva mudou de estado desde o último
      // carregamento (ex.: recebeu um pagamento) — recarrega pra refletir a realidade
      await carregarReservas(cliente.id);
    } finally {
      setExcluindoId(null);
    }
  }

  if (!cliente) {
    return (
      <div className="cliente-container">
        <h1>Minhas Reservas</h1>
        <p className="cliente-msg">
          Você precisa <Link to="/login">entrar</Link> para ver suas reservas.
        </p>
      </div>
    );
  }

  return (
    <div className="cliente-container">
      <h1>Minhas Reservas</h1>
      <p className="cliente-sub">Olá, {cliente.nome}</p>

      {loading ? <p className="cliente-msg">Carregando...</p> : null}
      {error ? <p className="cliente-msg erro">{error}</p> : null}
      {!loading && !error && alugueis.length === 0 ? (
        <p className="cliente-msg">Você ainda não tem nenhuma reserva.</p>
      ) : null}
      {cancelError ? <p className="cliente-msg erro">{cancelError}</p> : null}
      {excluirError ? <p className="cliente-msg erro">{excluirError}</p> : null}

      <div className="cliente-lista">
        {alugueis.map((aluguel) => (
          <div key={aluguel.id} className="cliente-card">
            <div className="cliente-card-header">
              <span className="cliente-card-quarto">
                #{aluguel.quartoId} - {aluguel.tipoQuarto}
              </span>
              <span className={`cliente-status status-${aluguel.status.toLowerCase()}`}>
                {STATUS_LABEL[aluguel.status] ?? aluguel.status}
              </span>
            </div>
            <p>
              <strong>Entrada:</strong> {new Date(aluguel.dataEntrada).toLocaleString('pt-BR')}
            </p>
            <p>
              <strong>Saída:</strong> {new Date(aluguel.dataSaida).toLocaleString('pt-BR')}
            </p>
            <p>
              <strong>Diárias:</strong> {aluguel.quantidadeDiarias}
            </p>
            <p>
              <strong>Valor final:</strong> R$ {Number(aluguel.valorFinal).toFixed(2)}
            </p>
            <p>
              <strong>Pagamento:</strong>{' '}
              <span className={`cliente-status ${aluguel.pago ? 'status-finalizado' : 'status-cancelado'}`}>
                {aluguel.pago ? 'Pago' : 'Pendente'}
              </span>
            </p>

            <div className="cliente-card-acoes">
              {aluguel.status === 'ATIVO' && !aluguel.pago ? (
                <button
                  type="button"
                  className="cliente-btn-pagar"
                  onClick={() => navigate(`/pagamento?aluguelId=${aluguel.id}`)}
                >
                  Pagar
                </button>
              ) : null}

              {aluguel.status === 'ATIVO' ? (
                <button
                  type="button"
                  className="cliente-btn-cancelar"
                  onClick={() => handleCancelar(aluguel.id)}
                  disabled={cancelandoId === aluguel.id}
                >
                  {cancelandoId === aluguel.id ? 'Cancelando...' : 'Cancelar Reserva'}
                </button>
              ) : null}

              {aluguel.status === 'CANCELADO' ? (
                <button
                  type="button"
                  className="cliente-btn-excluir"
                  onClick={() => handleExcluir(aluguel.id)}
                  disabled={excluindoId === aluguel.id}
                >
                  {excluindoId === aluguel.id ? 'Excluindo...' : 'Excluir Reserva'}
                </button>
              ) : null}
            </div>
          </div>
        ))}
      </div>

      {notificacoes.length > 0 ? (
        <div className="cliente-notificacoes">
          <h2>Notificações</h2>
          <ul className="cliente-notif-lista">
            {notificacoes.map((mensagem, index) => (
              <li key={index} className="cliente-notif-item">{mensagem}</li>
            ))}
          </ul>
        </div>
      ) : null}
    </div>
  );
}
