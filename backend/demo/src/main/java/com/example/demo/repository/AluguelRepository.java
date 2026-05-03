package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
    
}
