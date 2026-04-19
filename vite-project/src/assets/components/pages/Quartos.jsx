import { useParams } from "react-router-dom";
import quartos from '../../../data/quartos'

function Quartos() {

  // pega o id da URL (ex: /quartos/1)
  const params = useParams();
  const id = Number(params.id);

  return (
    <div>
      <h2>Quartos da residência {id}</h2>

      {quartos.map((q) => {
        if (q.residenciaId === id) {
          return (
            <div key={q.id}>
              <p>Tipo: {q.tipo}</p>
              <p>Valor: {q.valor}</p>
              <p>Ar: {q.ar ? "Sim" : "Não"}</p>
              <p>Hidro: {q.hidro ? "Sim" : "Não"}</p>
              <hr />
            </div>
          );
        }
      })}

    </div>
  );
}

export default Quartos;