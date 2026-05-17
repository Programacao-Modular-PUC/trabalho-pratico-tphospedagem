package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuartoRequestDTO;
import com.example.demo.dto.QuartoResponseDTO;
import com.example.demo.service.QuartoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/quartos")
public class QuartoController {
    
    private final QuartoService service;

    public QuartoController(QuartoService service) {
        this.service = service;
    }

    @GetMapping
    public List<QuartoResponseDTO> listar(@RequestParam(required = false) Long residenciaId){
        if (residenciaId != null) {
            return service.listarPorResidencia(residenciaId);
        }

        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuartoResponseDTO salvarQuarto(@Valid @RequestBody QuartoRequestDTO q) {
        return service.salvar(q);
    }
}
