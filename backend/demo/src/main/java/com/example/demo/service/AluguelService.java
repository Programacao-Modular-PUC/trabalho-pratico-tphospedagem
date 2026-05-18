package com.example.demo.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AluguelRequestDTO;
import com.example.demo.dto.AluguelResponseDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Aluguel;
import com.example.demo.model.Cliente;
import com.example.demo.model.Quarto;
import com.example.demo.model.enums.AluguelStatus;
import com.example.demo.repository.AluguelRepository;

@Service
public class AluguelService {

    private static final LocalTime HORARIO_REFERENCIA_DIARIA = LocalTime.NOON;

    private final AluguelRepository repository;
    private final ClienteService clienteService;
    private final QuartoService quartoService;
    private final Clock clock;

    public AluguelService(
        AluguelRepository repository,
        ClienteService clienteService,
        QuartoService quartoService,
        Clock clock
    ) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.quartoService = quartoService;
        this.clock = clock;
    }

    public List<AluguelResponseDTO> listar(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public List<AluguelResponseDTO> listarHistoricoPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this::toResponse).toList();
    }

    public AluguelResponseDTO salvar(AluguelRequestDTO dto ){
        validarDatas(dto.dataEntrada(), dto.dataSaida());

        Cliente cliente = clienteService.buscarPorId(dto.clienteId());
        Quarto quarto = quartoService.buscarPorId(dto.quartoId());

        validarCapacidade(quarto, dto.quantidadeHospedes());
        validarDisponibilidade(quarto.getId(), dto.dataEntrada(), dto.dataSaida());

        int diarias = calcularDiarias(dto.dataEntrada(), dto.dataSaida());
        BigDecimal valorDiaria = quarto.calcularValorDiaria(dto.quantidadeHospedes());
        BigDecimal valorFinal = valorDiaria.multiply(BigDecimal.valueOf(diarias));

        Aluguel aluguel = new Aluguel();
        aluguel.setDataEntrada(dto.dataEntrada());
        aluguel.setDataSaida(dto.dataSaida());
        aluguel.setQuantidadeDiarias(diarias);
        aluguel.setValorFinal(valorFinal);
        aluguel.setCliente(cliente);
        aluguel.setQuarto(quarto);
        aluguel.setStatus(calcularStatusInicial(dto.dataEntrada()));
        aluguel.setRecibo(gerarRecibo(cliente, quarto, diarias, valorFinal));

        return toResponse(repository.save(aluguel));
    }

    public AluguelResponseDTO finalizar(Long aluguelId) {
        Aluguel aluguel = repository.findById(aluguelId)
            .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado: " + aluguelId));

        aluguel.setStatus(AluguelStatus.FINALIZADO);
        return toResponse(repository.save(aluguel));
    }

    private void validarDatas(LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        if (!dataSaida.isAfter(dataEntrada)) {
            throw new BusinessRuleException("Data de saída deve ser maior que a data de entrada");
        }
    }

    private void validarCapacidade(Quarto quarto, int quantidadeHospedes) {
        if (quantidadeHospedes > quarto.getCapacidadeMaxima()) {
            throw new BusinessRuleException("Quantidade de hóspedes excede a capacidade do quarto");
        }
    }

    private void validarDisponibilidade(Long quartoId, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        boolean ocupado = repository.existsByQuartoIdAndStatusAndDataEntradaLessThanAndDataSaidaGreaterThan(
            quartoId,
            AluguelStatus.ATIVO,
            dataSaida,
            dataEntrada
        );

        if (ocupado) {
            throw new BusinessRuleException("Quarto indisponível para o período informado");
        }
    }

    private int calcularDiarias(LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        LocalDate entradaData = dataEntrada.toLocalDate();
        LocalDate saidaData = dataSaida.toLocalDate();

        long diferencaDias = ChronoUnit.DAYS.between(entradaData, saidaData);
        int diarias = diferencaDias <= 0 ? 1 : (int) diferencaDias;

        if (dataEntrada.toLocalTime().isAfter(HORARIO_REFERENCIA_DIARIA)) {
            diarias++;
        }

        if (!dataSaida.toLocalTime().isBefore(HORARIO_REFERENCIA_DIARIA)) {
            diarias++;
        }

        return Math.max(diarias, 1);
    }

    private AluguelStatus calcularStatusInicial(LocalDateTime dataEntrada) {
        LocalDateTime agora = LocalDateTime.now(clock);
        return dataEntrada.isAfter(agora) ? AluguelStatus.ATIVO : AluguelStatus.ATIVO;
    }

    private String gerarRecibo(Cliente cliente, Quarto quarto, int diarias, BigDecimal valorFinal) {
        return "RECIBO" + System.lineSeparator()
            + "Cliente: " + cliente.getNome() + System.lineSeparator()
            + "Quarto: " + quarto.getId() + System.lineSeparator()
            + "Diárias: " + diarias + System.lineSeparator()
            + "Valor final: R$ " + valorFinal;
    }

    private AluguelResponseDTO toResponse(Aluguel aluguel) {
        return new AluguelResponseDTO(
            aluguel.getId(),
            aluguel.getDataEntrada(),
            aluguel.getDataSaida(),
            aluguel.getQuantidadeDiarias(),
            aluguel.getValorFinal(),
            aluguel.getStatus(),
            aluguel.getRecibo(),
            aluguel.getCliente().getId(),
            aluguel.getCliente().getNome(),
            aluguel.getQuarto().getId(),
            aluguel.getQuarto().getClass().getSimpleName()
        );
    }

}

