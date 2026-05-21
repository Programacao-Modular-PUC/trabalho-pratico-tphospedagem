package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

	List<Quarto> findByResidenciaId(Long residenciaId);
}
