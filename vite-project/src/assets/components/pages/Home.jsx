import residencias from '../../../data/residencia'
import CardResidencias from '../layout/CardResidencias.jsx'
import styles from './Home.module.css'
function Home(){

    const destaque = residencias.slice(0, 3);


    return(
    <div className={styles.container_cards}>
            <h2>Destaques</h2>
            <div className={styles.cards_wrapper}>
                {destaque.map((r) => (
                    <CardResidencias key={r.id} residencia={r} />
                ))}
            </div>
        </div>
    )
}

export default Home