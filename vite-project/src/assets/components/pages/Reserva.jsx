import { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import quartos from "../../../data/quartos";
import "./Reserva.css";

function Reserva() {
  const [searchParams] = useSearchParams();
  const quartoId = Number(searchParams.get("quartoId"));
  const quarto = quartos.find((q) => q.id === quartoId);

  const [solicitaBerco, setSolicitaBerco] = useState(false);

  function calcularDiaria() {
    if (!quarto) return 0;
    let total = quarto.valorBase;
    if (quarto.tipo === "INDIVIDUAL") {
      const camas = quarto.numeroCamas || 1;
      if (camas > 1) total += (camas - 1) * quarto.adicionalPorCama;
    } else if (quarto.tipo === "DUPLO") {
      total += quarto.adicionalConforto || 0;
      if (solicitaBerco && quarto.permiteBerco) total += quarto.adicionalBerco || 0;
    }
    return total;
  }

  return (
    <div className="reserva-container">
      <h1>Reserva de Hospedagem</h1>

      {quarto && (
        <div className="quarto-info">
          <p>Quarto: {quarto.tipo} {quarto.tipoCama ? `— ${quarto.tipoCama}` : ""}</p>
          <p>Diária estimada: <strong>R$ {calcularDiaria().toFixed(2)}</strong></p>
        </div>
      )}

      <form className="reserva-form">
        <label>Nome:</label>
        <input type="text" />

        <label>CPF:</label>
        <input type="text" />

        <label>Data de Entrada:</label>
        <input type="datetime-local" />

        <label>Data de Saída:</label>
        <input type="datetime-local" />

        {quarto?.tipo === "DUPLO" && quarto.permiteBerco && (
          <label>
            <input
              type="checkbox"
              checked={solicitaBerco}
              onChange={(e) => setSolicitaBerco(e.target.checked)}
            />
            {" "}Solicitar berço (+R$ {quarto.adicionalBerco?.toFixed(2)})
          </label>
        )}

        <label>
          <input type="checkbox" /> Ar condicionado
        </label>
        <label>
          <input type="checkbox" /> Hidromassagem
        </label>

        <button type="submit">Reservar</button>
      </form>
    </div>
  );
}

export default Reserva;