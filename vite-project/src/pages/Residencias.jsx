import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './Residencias.module.css';
import casa1 from '../assets/images/casa1.jpg';
import casa2 from '../assets/images/casa2.jpg';
import casa3 from '../assets/images/casa3.jpg';

export default function Residencias() {
  const imagensFallback = [casa1, casa2, casa3];
  const imagemPorResidencia = {
    'Residência Bela Vista': casa1,
    'Residência Jardim dos Sonhos': casa2,
    'Residência Sol Nascente': casa3,
  };
  const [residencias, setResidencias] = useState([]);
  const [quartos, setQuartos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState('');

  useEffect(() => {
    async function carregar() {
      try {
        setLoading(true);
        setErro('');

        const [resResidencias, resQuartos] = await Promise.all([
          fetch('http://localhost:8080/residencias'),
          fetch('http://localhost:8080/quartos'),
        ]);

        if (!resResidencias.ok || !resQuartos.ok) {
          throw new Error('Falha ao carregar residências.');
        }

        const residenciasData = await resResidencias.json();
        const quartosData = await resQuartos.json();

        setResidencias(residenciasData);
        setQuartos(quartosData);
      } catch (error) {
        setErro(error.message || 'Erro ao carregar dados.');
      } finally {
        setLoading(false);
      }
    }

    carregar();
  }, []);

  return (
    <main className={styles.page}>
      <h1 className={styles.titulo}>Todas as Residências</h1>

      {loading && <p>Carregando residências...</p>}
      {erro && <p>{erro}</p>}

      <ul className={styles.lista}>
        {residencias.map((res) => {
          const quartosResidencia = quartos.filter(
            (q) => q.residenciaId === res.id
          );
          const imagemPadrao = imagemPorResidencia[res.nome] || imagensFallback[(res.id - 1) % imagensFallback.length];
          const imagemPrincipal = imagemPadrao || res.imagem;

          return (
            <li key={res.id} className={styles.card}>
              <div className={styles.imagemWrapper}>
                <img
                  src={imagemPrincipal}
                  alt={`Foto de ${res.nome}`}
                  className={styles.imagem}
                  onError={(event) => {
                    const fallback = res.imagem || imagemPadrao;

                    if (event.currentTarget.src !== fallback) {
                      event.currentTarget.src = fallback;
                    }
                  }}
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
                          <span>R$ {Number(q.valorBase).toFixed(2)}/noite</span>
                          <span className={styles.quartoDivider}>|</span>
                          <span>{q.capacidadeMaxima} Hóspede{q.capacidadeMaxima !== 1 ? 's' : ''}</span>
                        </li>
                      ))}
                    </ul>
                    <Link to={`/quartos/${res.id}`} className={styles.btnVerQuartos}>Ver Quartos</Link>
                  </div>
                )}
              </div>

              <div className={styles.acoes}>
                <Link to={`/quartos/${res.id}`} className={`${styles.btn} ${styles.btnSaiba}`}>
                  Ver detalhes
                </Link>
              </div>
            </li>
          );
        })}
      </ul>
    </main>
  );
}
