package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.QuartoRequestDTO;
import com.example.demo.dto.QuartoResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Quarto;
import com.example.demo.model.QuartoDuplo;
import com.example.demo.model.QuartoFamilia;
import com.example.demo.model.QuartoIndividual;
import com.example.demo.model.Residencia;
import com.example.demo.model.enums.TipoQuarto;
import com.example.demo.repository.QuartoRepository;

@Service
public class QuartoService {

    private final QuartoRepository repository;
    private final ResidenciaService residenciaService;

    public QuartoService(QuartoRepository repository, ResidenciaService residenciaService) {
        this.repository = repository;
        this.residenciaService = residenciaService;
    }

    public List<QuartoResponseDTO> listar(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public List<QuartoResponseDTO> listarPorResidencia(Long residenciaId) {
        return repository.findByResidenciaId(residenciaId).stream().map(this::toResponse).toList();
    }

    public QuartoResponseDTO salvar(QuartoRequestDTO dto){
        Residencia residencia = residenciaService.buscarPorId(dto.residenciaId());
        Quarto quarto = criarQuartoPorTipo(dto);
        quarto.setResidencia(residencia);

        return toResponse(repository.save(quarto));
    }

    public Quarto buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado: " + id));
    }

    private Quarto criarQuartoPorTipo(QuartoRequestDTO dto) {
        validarCamposComuns(dto);

        return switch (dto.tipo()) {
            case INDIVIDUAL -> criarQuartoIndividual(dto);
            case DUPLO -> criarQuartoDuplo(dto);
            case FAMILIA -> criarQuartoFamilia(dto);
        };
    }

    private Quarto criarQuartoIndividual(QuartoRequestDTO dto) {
        if (dto.quantidadeCamasSolteiro() <= 0) {
            throw new BusinessRuleException("Quarto individual precisa ter ao menos 1 cama de solteiro");
        }

        QuartoIndividual quarto = new QuartoIndividual();
        preencherCamposComuns(quarto, dto);
        quarto.setQuantidadeCamasSolteiro(dto.quantidadeCamasSolteiro());
        quarto.setTaxaCamaAdicional(valueOrZero(dto.taxaCamaAdicional()));

        if (quarto.getCapacidadeMaxima() > quarto.getQuantidadeCamasSolteiro()) {
            throw new BusinessRuleException("Capacidade do quarto individual deve ser proporcional ao número de camas");
        }

        return quarto;
    }

    private Quarto criarQuartoDuplo(QuartoRequestDTO dto) {
        QuartoDuplo quarto = new QuartoDuplo();
        preencherCamposComuns(quarto, dto);
        quarto.setCamaQueenKing(dto.camaQueenKing());
        quarto.setPermiteBerco(dto.permiteBerco());
        quarto.setTaxaBerco(valueOrZero(dto.taxaBerco()));
        quarto.setTaxaQueenKing(valueOrZero(dto.taxaQueenKing()));

        if (quarto.getCapacidadeMaxima() < 2) {
            throw new BusinessRuleException("Quarto duplo deve suportar ao menos 2 hóspedes");
        }

        return quarto;
    }

    private Quarto criarQuartoFamilia(QuartoRequestDTO dto) {
        if (dto.quantidadeAmbientes() <= 0) {
            throw new BusinessRuleException("Quarto família deve possuir pelo menos 1 ambiente");
        }

        QuartoFamilia quarto = new QuartoFamilia();
        preencherCamposComuns(quarto, dto);
        quarto.setQuantidadeAmbientes(dto.quantidadeAmbientes());
        quarto.setValorPorHospedeAdicional(valueOrZero(dto.valorPorHospedeAdicional()));
        quarto.setPercentualDescontoGrupo(valueOrZero(dto.percentualDescontoGrupo()));

        return quarto;
    }

    private void preencherCamposComuns(Quarto quarto, QuartoRequestDTO dto) {
        quarto.setValorBase(dto.valorBase());
        quarto.setPossuiAR(dto.possuiAR());
        quarto.setPossuiHidro(dto.possuiHidro());
        quarto.setCapacidadeMaxima(dto.capacidadeMaxima());
    }

    private void validarCamposComuns(QuartoRequestDTO dto) {
        if (dto.valorBase() == null || dto.valorBase().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("Valor base do quarto deve ser positivo");
        }

        if (dto.capacidadeMaxima() <= 0) {
            throw new BusinessRuleException("Capacidade máxima deve ser positiva");
        }
    }

    private QuartoResponseDTO toResponse(Quarto quarto) {
        TipoQuarto tipo = identificarTipo(quarto);

        Integer quantidadeCamasSolteiro = null;
        BigDecimal taxaCamaAdicional = null;
        Boolean camaQueenKing = null;
        Boolean permiteBerco = null;
        BigDecimal taxaBerco = null;
        BigDecimal taxaQueenKing = null;
        Integer quantidadeAmbientes = null;
        BigDecimal valorPorHospedeAdicional = null;
        BigDecimal percentualDescontoGrupo = null;

        if (quarto instanceof QuartoIndividual individual) {
            quantidadeCamasSolteiro = individual.getQuantidadeCamasSolteiro();
            taxaCamaAdicional = individual.getTaxaCamaAdicional();
        }

        if (quarto instanceof QuartoDuplo duplo) {
            camaQueenKing = duplo.isCamaQueenKing();
            permiteBerco = duplo.isPermiteBerco();
            taxaBerco = duplo.getTaxaBerco();
            taxaQueenKing = duplo.getTaxaQueenKing();
        }

        if (quarto instanceof QuartoFamilia familia) {
            quantidadeAmbientes = familia.getQuantidadeAmbientes();
            valorPorHospedeAdicional = familia.getValorPorHospedeAdicional();
            percentualDescontoGrupo = familia.getPercentualDescontoGrupo();
        }

        return new QuartoResponseDTO(
            quarto.getId(),
            tipo,
            quarto.getResidencia() == null ? null : quarto.getResidencia().getId(),
            quarto.getValorBase(),
            quarto.isPossuiAR(),
            quarto.isPossuiHidro(),
            quarto.getCapacidadeMaxima(),
            quantidadeCamasSolteiro,
            taxaCamaAdicional,
            camaQueenKing,
            permiteBerco,
            taxaBerco,
            taxaQueenKing,
            quantidadeAmbientes,
            valorPorHospedeAdicional,
            percentualDescontoGrupo
        );
    }

    private TipoQuarto identificarTipo(Quarto quarto) {
        if (quarto instanceof QuartoIndividual) {
            return TipoQuarto.INDIVIDUAL;
        }
        if (quarto instanceof QuartoDuplo) {
            return TipoQuarto.DUPLO;
        }
        if (quarto instanceof QuartoFamilia) {
            return TipoQuarto.FAMILIA;
        }

        throw new IllegalStateException("Tipo de quarto não mapeado");
    }

    private BigDecimal valueOrZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
    
}
