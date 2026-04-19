import { useParams, Link } from "react-router-dom";
import quartos from "../../../data/quartos";

function Quartos() {
  const params = useParams();
  const id = Number(params.id);

  const quartosFiltrados = quartos.filter(
    (q) => q.residenciaId === id
  );

  return (
    <div>
      <h2>Quartos da residência {id}</h2>

      {quartosFiltrados.length === 0 ? (
        <p>Nenhum quarto encontrado.</p>
      ) : (
        quartosFiltrados.map((q) => (
          <div key={q.id}>
            <p>Tipo: {q.tipo}</p>
            <p>Valor: R$ {q.valor}</p>
            <p>Ar: {q.ar ? "Sim" : "Não"}</p>
            <p>Hidro: {q.hidro ? "Sim" : "Não"}</p>
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