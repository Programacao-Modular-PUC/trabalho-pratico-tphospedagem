package com.example.demo.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Aluguel;
import com.example.demo.model.enums.AluguelStatus;

public interface AluguelRepository extends JpaRepository<Aluguel, Long>{

	boolean existsByQuartoIdAndStatusAndDataEntradaLessThanAndDataSaidaGreaterThan(
		Long quartoId,
		AluguelStatus status,
		LocalDateTime dataSaida,
		LocalDateTime dataEntrada
	);

	List<Aluguel> findByClienteId(Long clienteId);
}
