package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AluguelRequestDTO;
import com.example.demo.dto.AluguelResponseDTO;
import com.example.demo.service.AluguelService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    
    private final AluguelService service;

    public AluguelController(AluguelService service) {
        this.service = service;
    }

    @GetMapping
    public List<AluguelResponseDTO> listar(@RequestParam(required = false) Long clienteId) {
        if (clienteId != null) {
            return service.listarHistoricoPorCliente(clienteId);
        }

        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AluguelResponseDTO salvarAluguel(@Valid @RequestBody AluguelRequestDTO a) {
        return service.salvar(a);
    }

    @PutMapping("/{id}/finalizar")
    public AluguelResponseDTO finalizarAluguel(@PathVariable Long id) {
        return service.finalizar(id);
    }

    @PutMapping("/{id}/cancelar")
    public AluguelResponseDTO cancelarAluguel(@PathVariable Long id) {
        return service.cancelar(id);
    }
    
    
}
