import { useParams, Link } from "react-router-dom";
import quartos from "../../../data/quartos";

function Quartos() {
  const params = useParams();
  const id = Number(params.id);
  const quartosFiltrados = quartos.filter((q) => q.residenciaId === id);

  function calcularDiaria(q, comBerco = false) {
    let total = q.valorBase;
    if (q.tipo === "INDIVIDUAL") {
      const camas = q.numeroCamas || 1;
      if (camas > 1) total += (camas - 1) * q.adicionalPorCama;
    } else if (q.tipo === "DUPLO") {
      total += q.adicionalConforto || 0;
      if (comBerco && q.permiteBerco) total += q.adicionalBerco || 0;
    }
    return total;
  }

  return (
    <div>
      <h2>Quartos da residência {id}</h2>
      {quartosFiltrados.length === 0 ? (
        <p>Nenhum quarto encontrado.</p>
      ) : (
        quartosFiltrados.map((q) => (
          <div key={q.id}>
            <p>Tipo: {q.tipo}</p>
            {q.tipo === "INDIVIDUAL" && (
              <>
                <p>Número de camas: {q.numeroCamas}</p>
                <p>Capacidade: {q.numeroCamas} hóspede(s)</p>
                <p>Diária: R$ {calcularDiaria(q).toFixed(2)}</p>
              </>
            )}
            {q.tipo === "DUPLO" && (
              <>
                <p>Cama: {q.tipoCama}</p>
                <p>Permite berço: {q.permiteBerco ? "Sim" : "Não"}</p>
                <p>Diária sem berço: R$ {calcularDiaria(q, false).toFixed(2)}</p>
                {q.permiteBerco && (
                  <p>Diária com berço: R$ {calcularDiaria(q, true).toFixed(2)}</p>
                )}
              </>
            )}
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