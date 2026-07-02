package com.example.demo.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.example.demo.dto.PagamentoRequestDTO;
import com.example.demo.dto.PagamentoResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.gerenciador.GerenciadorDePagamento;
import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Aluguel;
import com.example.demo.model.Pagamento;
import com.example.demo.model.Strategy.MeioDePagamento;
import com.example.demo.repository.AluguelRepository;
import com.example.demo.repository.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final AluguelRepository aluguelRepository;
    private final SystemLogger logger = SystemLogger.getInstance();

    public PagamentoService(PagamentoRepository repository,
                            AluguelRepository aluguelRepository) {
        this.repository = repository;
        this.aluguelRepository = aluguelRepository;
    }

    public PagamentoResponseDTO processar(PagamentoRequestDTO dto) {
        Aluguel aluguel = aluguelRepository.findById(dto.aluguelId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Aluguel não encontrado: " + dto.aluguelId()));

        // Usa o Singleton para obter a estratégia correta
        MeioDePagamento estrategia = GerenciadorDePagamento
            .getInstance()
            .obterEstrategia(dto.tipo());

        BigDecimal valorOriginal = aluguel.getValorFinal();
        BigDecimal taxa = estrategia.calcularTaxa(valorOriginal);
        BigDecimal valorFinal = valorOriginal.add(taxa);
        String resultado = estrategia.processar(valorOriginal);

        Pagamento pagamento = new Pagamento();
        pagamento.setAluguel(aluguel);
        pagamento.setTipo(dto.tipo());
        pagamento.setValorOriginal(valorOriginal);
        pagamento.setTaxa(taxa);
        pagamento.setValorFinal(valorFinal);
        pagamento.setResultado(resultado);

        Pagamento salvo = repository.save(pagamento);

        logger.log("PAGAMENTO_PROCESSADO",
            "Aluguel ID: " + dto.aluguelId() +
            " | Meio: " + dto.tipo() +
            " | Valor: R$ " + valorFinal);

        return toResponse(salvo);
    }

    private PagamentoResponseDTO toResponse(Pagamento p) {
        return new PagamentoResponseDTO(
            p.getId(),
            p.getAluguel().getId(),
            p.getTipo(),
            p.getValorOriginal(),
            p.getTaxa(),
            p.getValorFinal(),
            p.getResultado()
        );
    }
}