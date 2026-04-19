import "./Reserva.css";

function Reserva() {
  return (
    <div className="reserva-container">
      <h1>Reserva de Hospedagem</h1>

      <form className="reserva-form">
        <label>Nome:</label>
        <input type="text" />

        <label>CPF:</label>
        <input type="text" />

        <label>Data de Entrada:</label>
        <input type="datetime-local" />

        <label>Data de Saída:</label>
        <input type="datetime-local" />

        <label>Tipo de Quarto:</label>
        <select>
          <option>Individual</option>
          <option>Casal</option>
        </select>

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