import styles from './CardResidencias.module.css'
import { useNavigate } from 'react-router-dom';
function CardResidencia({ residencia }) {


  const navigate = useNavigate();

  function handleVerQuartos() {   // função para pegar o id dos quartos para navegação (botao ver quartos das residencias)
    navigate(`/quartos/${residencia.id}`);
  }

  return (
    <div className={styles.card}>
      <img src={residencia.imagem} alt="Residência" />

      <div className={styles.card_content}>
        <p><strong>Endereço:</strong> {residencia.endereco}</p>
        <p><strong>Bairro:</strong> {residencia.bairro}</p>
        <p><strong>Telefone:</strong> {residencia.telefone}</p>

        <button onClick={handleVerQuartos}>
          Ver quartos
        </button>
      </div>
    </div>
  );
}

export default CardResidencia;