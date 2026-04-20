import styles from './Residencias.module.css';
import residencias from '../../../data/residencia.js';
import quartos from '../../../data/quartos.js';

export default function Residencias() {
  return (
    <main className={styles.page}>
      <h1 className={styles.titulo}>Todas as Residências</h1>

      <ul className={styles.lista}>
        {residencias.map((res) => {
          const quartosResidencia = quartos.filter(
            (q) => q.residenciaId === res.id
          );

          return (
            <li key={res.id} className={styles.card}>
              <div className={styles.imagemWrapper}>
                <img
                  src={res.imagem}
                  alt={`Foto de ${res.nome}`}
                  className={styles.imagem}
                />
              </div>

              <div className={styles.info}>
                <h2 className={styles.nome}>{res.nome}</h2>

                <p className={styles.detalhe}>
                  <span className={styles.label}>Endereço:</span> {res.endereco}
                  &nbsp;&nbsp;
                  <span className={styles.label}>Bairro:</span> {res.bairro}
                  &nbsp;&nbsp;
                  <span className={styles.label}>Telefone:</span> {res.telefone}
                </p>

                {quartosResidencia.length > 0 && (
                  <div className={styles.quartosBox}>
                    <p className={styles.quartosLabel}>
                      Quartos Disponíveis nesta Residência
                    </p>
                    <ul className={styles.quartosList}>
                      {quartosResidencia.map((q) => (
                        <li key={q.id} className={styles.quartoItem}>
                          <span className={styles.quartoNome}>{q.tipo}</span>
                          <span className={styles.quartoDivider}>|</span>
                          <span>R$ {Number(q.valor).toFixed(2)}/noite</span>
                          <span className={styles.quartoDivider}>|</span>
                          <span>{q.capacidade} Hóspede{q.capacidade !== 1 ? 's' : ''}</span>
                        </li>
                      ))}
                    </ul>
                    <button className={styles.btnVerQuartos}>Ver Quartos</button>
                  </div>
                )}
              </div>

              <div className={styles.acoes}>
                <button className={`${styles.btn} ${styles.btnSaiba}`}>
                  Saiba Mais
                </button>
              </div>
            </li>
          );
        })}
      </ul>
    </main>
  );
}
