import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

function Quartos() {
  const params = useParams();
  const id = Number(params.id);
  const [quartos, setQuartos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    async function carregarQuartos() {
      try {
        setLoading(true);
        setErro("");

        const response = await fetch(`http://localhost:8080/quartos?residenciaId=${id}`);

        if (!response.ok) {
          throw new Error("Não foi possível carregar os quartos desta residência.");
        }

        const data = await response.json();
        setQuartos(data);
      } catch (error) {
        setErro(error.message || "Erro ao carregar quartos.");
      } finally {
        setLoading(false);
      }
    }

    if (!Number.isNaN(id)) {
      carregarQuartos();
    }
  }, [id]);

  return (
    <div>
      <h2>Quartos da residência {id}</h2>

      {loading && <p>Carregando quartos...</p>}
      {erro && <p>{erro}</p>}

      {!loading && quartos.length === 0 ? (
        <p>Nenhum quarto encontrado.</p>
      ) : (
        quartos.map((q) => (
          <div key={q.id}>
            <p>Tipo: {q.tipo}</p>
            <p>Valor: R$ {Number(q.valorBase).toFixed(2)}</p>
            <p>Capacidade: {q.capacidadeMaxima}</p>
            <p>Ar: {q.possuiAR ? "Sim" : "Não"}</p>
            <p>Hidro: {q.possuiHidro ? "Sim" : "Não"}</p>
            <Link to={`/reserva?quartoId=${q.id}`}>
              <button>Reservar quarto</button>
            </Link>
            <hr />
          </div>
        ))
      )}
    </div>
  );
}

export default Quartos;