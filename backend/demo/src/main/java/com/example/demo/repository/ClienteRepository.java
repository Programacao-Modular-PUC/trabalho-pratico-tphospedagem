package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    boolean existsByEmail(String email);

    Optional<Cliente> findByEmail(String email);

}
