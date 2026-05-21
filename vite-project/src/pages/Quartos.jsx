import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import Container from "../components/layout/Container";
import styles from './Quartos.module.css';

function Quartos() {
  const params = useParams();
  const id = Number(params.id);
  const porResidencia = !Number.isNaN(id);
  const [quartos, setQuartos] = useState([]);
  const [residencia, setResidencia] = useState(null);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    async function carregarQuartos() {
      try {
        setLoading(true);
        setErro("");

        const endpoint = porResidencia ? `/api/quartos?residenciaId=${id}` : '/api/quartos';
        const response = await fetch(endpoint);

        if (!response.ok) {
          throw new Error("Não foi possível carregar os quartos.");
        }

        const data = await response.json();
        setQuartos(data);

        // Se for por residência, carrega também os dados da residência
        if (porResidencia) {
          const resRes = await fetch(`/api/residencias/${id}`);
          if (resRes.ok) {
            const resData = await resRes.json();
            setResidencia(resData);
          }
        }
      } catch (error) {
        setErro(error.message || "Erro ao carregar quartos.");
      } finally {
        setLoading(false);
      }
    }

    carregarQuartos();
  }, [id, porResidencia]);

  const getTipoQuartoLabel = (tipo) => {
    const labels = {
      INDIVIDUAL: "Quarto Individual",
      DUPLO: "Quarto Duplo",
      FAMILIA: "Quarto Família"
    };
    return labels[tipo] || tipo;
  };

  return (
    <Container>
      <main className={styles.page}>
        <h1 className={styles.titulo}>
          {porResidencia && residencia ? `Quartos - ${residencia.nome}` : 'Todos os Quartos Disponíveis'}
        </h1>

        {loading && <p className={styles.loading}>Carregando quartos...</p>}
        {erro && <p className={styles.erro}>{erro}</p>}

        {!loading && quartos.length === 0 ? (
          <p className={styles.vazio}>Nenhum quarto encontrado.</p>
        ) : (
          <ul className={styles.lista}>
            {quartos.map((q) => (
              <li key={q.id} className={styles.card}>
                <div className={styles.header}>
                  <h2 className={styles.tipoQuarto}>{getTipoQuartoLabel(q.tipo)}</h2>
                  <span className={styles.preco}>R$ {Number(q.valorBase).toFixed(2)}<small>/noite</small></span>
                </div>

                <div className={styles.detalhes}>
                  <div className={styles.detalhe}>
                    <span className={styles.label}>Capacidade:</span>
                    <span>{q.capacidadeMaxima} Hóspede{q.capacidadeMaxima !== 1 ? 's' : ''}</span>
                  </div>
                  <div className={styles.detalhe}>
                    <span className={styles.label}>Ar Condicionado:</span>
                    <span>{q.possuiAR ? "✓ Sim" : "✗ Não"}</span>
                  </div>
                  <div className={styles.detalhe}>
                    <span className={styles.label}>Hidromassagem:</span>
                    <span>{q.possuiHidro ? "✓ Sim" : "✗ Não"}</span>
                  </div>
                </div>

                <Link to={`/reserva?quartoId=${q.id}`} className={styles.btnReservar}>
                  Reservar
                </Link>
              </li>
            ))}
          </ul>
        )}
      </main>
    </Container>
  );
}

export default Quartos;