import { useEffect, useState } from 'react'
import { useSearchParams } from 'react-router-dom'
import Container from '../components/layout/Container'
import './Pagamento.css'

const MEIOS_PAGAMENTO = [
  { tipo: 'PIX', label: 'PIX', descTaxa: 'Sem taxa' },
  { tipo: 'CARTAO_CREDITO', label: 'Cartão de Crédito', descTaxa: 'Taxa de 3%' },
  { tipo: 'CARTAO_DEBITO', label: 'Cartão de Débito', descTaxa: 'Taxa de 1%' },
  { tipo: 'DINHEIRO', label: 'Dinheiro', descTaxa: 'Sem taxa' },
]

function Pagamento() {
  const [searchParams] = useSearchParams()
  const aluguelId = searchParams.get('aluguelId')

  const [aluguel, setAluguel] = useState(null)
  const [loading, setLoading] = useState(true)
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [resultado, setResultado] = useState(null)
  const [meioPagamento, setMeioPagamento] = useState('PIX')

  useEffect(() => {
    async function carregarAluguel() {
      try {
        setLoading(true)
        setError('')
        const res = await fetch(`/api/alugueis/${aluguelId}`)
        if (!res.ok) throw new Error('Não foi possível carregar a reserva.')
        const data = await res.json()
        setAluguel(data)
      } catch (err) {
        setError(err.message || 'Erro ao carregar reserva.')
      } finally {
        setLoading(false)
      }
    }

    if (aluguelId) carregarAluguel()
  }, [aluguelId])

  async function handlePagar() {
    setSubmitting(true)
    setError('')
    setSuccess('')
    setResultado(null)

    try {
      const res = await fetch('/api/pagamentos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ aluguelId: Number(aluguelId), tipo: meioPagamento }),
      })

      const data = await res.json()
      if (!res.ok) throw new Error(data.message || 'Erro ao processar pagamento.')

      setResultado(data)
      setSuccess('Pagamento realizado com sucesso!')
    } catch (err) {
      setError(err.message || 'Erro inesperado.')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <Container>
      <div className="pagamento-container">
        <h1 className="pagamento-titulo">Pagamento</h1>

        {loading && <p className="pagamento-msg">Carregando reserva...</p>}
        {error && <p className="pagamento-msg erro">{error}</p>}
        {success && <p className="pagamento-msg sucesso">{success}</p>}

        {aluguel && !resultado && (
          <>
            <div className="pagamento-card">
              <p className="pagamento-secao-label">Resumo da reserva</p>
              <div className="pagamento-linha">
                <span>Cliente</span>
                <span>{aluguel.clienteNome}</span>
              </div>
              <div className="pagamento-linha">
                <span>Quarto</span>
                <span>#{aluguel.quartoId} - {aluguel.tipoQuarto}</span>
              </div>
              <div className="pagamento-linha">
                <span>Diárias</span>
                <span>{aluguel.quantidadeDiarias}</span>
              </div>
              <div className="pagamento-linha total">
                <span>Valor total</span>
                <strong>R$ {Number(aluguel.valorFinal).toFixed(2)}</strong>
              </div>
            </div>

            <div className="pagamento-card">
              <p className="pagamento-secao-label">Meio de pagamento</p>
              <div className="pagamento-grid">
                {MEIOS_PAGAMENTO.map((meio) => (
                  <div
                    key={meio.tipo}
                    className={`pagamento-opcao ${meioPagamento === meio.tipo ? 'selected' : ''}`}
                    onClick={() => setMeioPagamento(meio.tipo)}
                  >
                    <span className="pagamento-nome">{meio.label}</span>
                    <small>{meio.descTaxa}</small>
                  </div>
                ))}
              </div>
            </div>

            <button
              className="pagamento-btn"
              onClick={handlePagar}
              disabled={submitting}
            >
              {submitting ? 'Processando...' : 'Confirmar pagamento'}
            </button>
          </>
        )}

        {resultado && (
          <div className="pagamento-card">
            <p className="pagamento-secao-label">Comprovante</p>
            <div className="pagamento-linha">
              <span>Meio de pagamento</span>
              <span>{resultado.tipo}</span>
            </div>
            <div className="pagamento-linha">
              <span>Valor original</span>
              <span>R$ {Number(resultado.valorOriginal).toFixed(2)}</span>
            </div>
            {Number(resultado.taxa) > 0 && (
              <div className="pagamento-linha">
                <span>Taxa</span>
                <span>R$ {Number(resultado.taxa).toFixed(2)}</span>
              </div>
            )}
            <div className="pagamento-linha total">
              <span>Total pago</span>
              <strong>R$ {Number(resultado.valorFinal).toFixed(2)}</strong>
            </div>
            <p className="pagamento-msg sucesso">{resultado.resultado}</p>
          </div>
        )}
      </div>
    </Container>
  )
}

export default Pagamento