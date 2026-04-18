import styles from './CardResidencias.module.css'

function CardResidencia({ residencia }) {
  return (
    <div className={styles.card}>
      <img src={residencia.imagem} alt="Residência" />

      <div className={styles.card_content}>
        <p><strong>Endereço:</strong> {residencia.endereco}</p>
        <p><strong>Bairro:</strong> {residencia.bairro}</p>
        <p><strong>Telefone:</strong> {residencia.telefone}</p>

        <button>Ver quartos</button>
      </div>
    </div>
  );
}

export default CardResidencia;