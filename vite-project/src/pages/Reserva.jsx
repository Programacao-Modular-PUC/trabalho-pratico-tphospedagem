import { useEffect, useMemo, useState } from 'react'
import { Link, useSearchParams } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'
import './Reserva.css'

const initialForm = {
  quartoId: '',
  dataEntrada: '',
  dataSaida: '',
  quantidadeHospedes: 1,
}

function Reserva() {
  const { cliente } = useAuth()
  const [searchParams] = useSearchParams()
  const [form, setForm] = useState(initialForm)
  const [quartos, setQuartos] = useState([])
  const [loading, setLoading] = useState(true)
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [resultado, setResultado] = useState(null)
  const [cancelando, setCancelando] = useState(false)
  const [cancelError, setCancelError] = useState('')

  useEffect(() => {
    async function carregarDados() {
      try {
        setLoading(true)
        setError('')

        const quartosRes = await fetch('/api/quartos')

        if (!quartosRes.ok) {
          throw new Error('Falha ao carregar quartos')
        }

        setQuartos(await quartosRes.json())
      } catch (err) {
        setError(err.message || 'Não foi possível carregar os dados iniciais.')
      } finally {
        setLoading(false)
      }
    }

    carregarDados()
  }, [])

  useEffect(() => {
    const quartoIdParam = searchParams.get('quartoId')
    if (quartoIdParam) {
      setForm((current) => ({ ...current, quartoId: quartoIdParam }))
    }
  }, [searchParams])

  const quartoSelecionado = useMemo(
    () => quartos.find((quarto) => String(quarto.id) === String(form.quartoId)),
    [quartos, form.quartoId],
  )

  const valorEstimado = useMemo(() => {
    if (!quartoSelecionado) return null
    return quartoSelecionado.valorBase ?? null
  }, [quartoSelecionado])

  function handleChange(event) {
    const { name, value } = event.target
    setForm((current) => ({
      ...current,
      [name]: value,
    }))
  }

  async function handleSubmit(event) {
    event.preventDefault()
    setSubmitting(true)
    setError('')
    setSuccess('')
    setResultado(null)

    try {
      const payload = {
        clienteId: cliente.id,
        quartoId: Number(form.quartoId),
        dataEntrada: form.dataEntrada,
        dataSaida: form.dataSaida,
        quantidadeHospedes: Number(form.quantidadeHospedes),
      }

      const response = await fetch('/api/alugueis', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.message || 'Não foi possível registrar o aluguel.')
      }

      setResultado(data)
      setSuccess('Aluguel registrado com sucesso.')
      setForm(initialForm)
    } catch (err) {
      setError(err.message || 'Erro inesperado ao salvar aluguel.')
    } finally {
      setSubmitting(false)
    }
  }

  async function handleCancelar() {
    if (!resultado?.id) return

    setCancelando(true)
    setCancelError('')

    try {
      const response = await fetch(`/api/alugueis/${resultado.id}/cancelar`, {
        method: 'PUT',
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.message || 'Não foi possível cancelar a reserva.')
      }

      setResultado(data)
      setSuccess('')
    } catch (err) {
      setCancelError(err.message || 'Erro inesperado ao cancelar a reserva.')
    } finally {
      setCancelando(false)
    }
  }

  if (!cliente) {
    return (
      <div className="reserva-container">
        <h1>Reserva de Hospedagem</h1>
        <p className="reserva-msg">
          Você precisa <Link to="/login">entrar</Link> para fazer uma reserva.
        </p>
      </div>
    )
  }

  return (
    <div className="reserva-container">
      <h1>Reserva de Hospedagem</h1>
      <p className="reserva-msg">Reservando como <strong>{cliente.nome}</strong></p>

      <form className="reserva-form" onSubmit={handleSubmit}>
        {loading ? <p className="reserva-msg">Carregando quartos...</p> : null}
        {error ? <p className="reserva-msg erro">{error}</p> : null}
        {success ? <p className="reserva-msg sucesso">{success}</p> : null}

        <label>Quarto:</label>
        <select name="quartoId" value={form.quartoId} onChange={handleChange} required>
          <option value="">Selecione um quarto</option>
          {quartos.map((quarto) => (
            <option key={quarto.id} value={quarto.id}>
              #{quarto.id} - {quarto.tipo} - R$ {Number(quarto.valorBase).toFixed(2)}
            </option>
          ))}
        </select>

        <label>Data de Entrada:</label>
        <input
          type="datetime-local"
          name="dataEntrada"
          value={form.dataEntrada}
          onChange={handleChange}
          required
        />

        <label>Data de Saída:</label>
        <input
          type="datetime-local"
          name="dataSaida"
          value={form.dataSaida}
          onChange={handleChange}
          required
        />

        <label>Quantidade de Hóspedes:</label>
        <input
          type="number"
          name="quantidadeHospedes"
          min="1"
          value={form.quantidadeHospedes}
          onChange={handleChange}
          required
        />

        {quartoSelecionado ? (
          <p className="reserva-msg">
            Quarto selecionado: {quartoSelecionado.tipo} | Capacidade: {quartoSelecionado.capacidadeMaxima} |
            Diária base: R$ {Number(valorEstimado ?? 0).toFixed(2)}
          </p>
        ) : null}

        <button type="submit" disabled={submitting || loading}>
          {submitting ? 'Salvando...' : 'Reservar'}
        </button>
      </form>

      {resultado ? (
        <div className="reserva-recibo">
          <h2>Recibo da Reserva</h2>
          <p><strong>Cliente:</strong> {resultado.clienteNome}</p>
          <p><strong>Quarto:</strong> #{resultado.quartoId} - {resultado.tipoQuarto}</p>
          <p><strong>Diárias:</strong> {resultado.quantidadeDiarias}</p>
          <p><strong>Valor final:</strong> R$ {Number(resultado.valorFinal).toFixed(2)}</p>
          <p>
            <strong>Status:</strong>{' '}
            <span className={`reserva-status status-${resultado.status?.toLowerCase()}`}>
              {resultado.status}
            </span>
          </p>
          <pre>{resultado.recibo}</pre>

          {cancelError ? <p className="reserva-msg erro">{cancelError}</p> : null}

          {resultado.status === 'ATIVO' ? (
            <button
              type="button"
              className="btn-cancelar"
              onClick={handleCancelar}
              disabled={cancelando}
            >
              {cancelando ? 'Cancelando...' : 'Cancelar Reserva'}
            </button>
          ) : null}
        </div>
      ) : null}
    </div>
  )
}

export default Reserva
