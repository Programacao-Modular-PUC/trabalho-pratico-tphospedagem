package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.QuartoRequestDTO;
import com.example.demo.exception.RecursoNaoPermitidoException;
import com.example.demo.model.Residencia;
import com.example.demo.model.enums.TipoQuarto;
import com.example.demo.repository.QuartoRepository;

@ExtendWith(MockitoExtension.class)
class QuartoServiceTest {

    @Mock
    private QuartoRepository repository;

    @Mock
    private ResidenciaService residenciaService;

    @Test
    void deveLancarRecursoNaoPermitidoQuandoBercoSolicitadoParaQuartoIndividual() {
        QuartoService service = new QuartoService(repository, residenciaService);

        Residencia residencia = new Residencia();
        residencia.setId(1L);
        when(residenciaService.buscarPorId(1L)).thenReturn(residencia);

        QuartoRequestDTO dto = new QuartoRequestDTO(
            TipoQuarto.INDIVIDUAL,
            1L,
            new BigDecimal("150.00"),
            true,
            false,
            1,
            1,
            BigDecimal.ZERO,
            false,
            true,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            0,
            null,
            null
        );

        RecursoNaoPermitidoException exception = assertThrows(
            RecursoNaoPermitidoException.class,
            () -> service.salvar(dto)
        );

        assertTrue(exception.getMessage().contains("Berço"));
        assertTrue(exception.getMessage().contains("Quarto Individual"));
    }
}
