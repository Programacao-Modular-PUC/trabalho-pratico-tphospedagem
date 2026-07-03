package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.AluguelRequestDTO;
import com.example.demo.exception.CapacidadeExcedidaException;
import com.example.demo.model.Cliente;
import com.example.demo.model.QuartoIndividual;
import com.example.demo.notificacao.NotificacaoService;
import com.example.demo.repository.AluguelRepository;
import com.example.demo.repository.PagamentoRepository;

@ExtendWith(MockitoExtension.class)
class AluguelServiceTest {

    @Mock
    private AluguelRepository repository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private QuartoService quartoService;

    @Mock
    private NotificacaoService notificacaoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Test
    void deveLancarCapacidadeExcedidaExceptionQuandoQuantidadeHospedesUltrapassaLimite() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        AluguelService service = new AluguelService(repository, clienteService, quartoService, clock, notificacaoService, pagamentoRepository);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");

        QuartoIndividual quarto = new QuartoIndividual();
        quarto.setId(1L);
        quarto.setCapacidadeMaxima(2);

        when(clienteService.buscarPorId(1L)).thenReturn(cliente);
        when(quartoService.buscarPorId(1L)).thenReturn(quarto);

        AluguelRequestDTO dto = new AluguelRequestDTO(
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(2),
            1L,
            1L,
            3
        );

        CapacidadeExcedidaException exception = assertThrows(
            CapacidadeExcedidaException.class,
            () -> service.salvar(dto)
        );

        assertTrue(exception.getMessage().contains("Limite: 2"));
        assertTrue(exception.getMessage().contains("Tentativa: 3"));
    }
}
