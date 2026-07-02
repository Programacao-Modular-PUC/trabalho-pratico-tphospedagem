package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResidenciaRequestDTO;
import com.example.demo.dto.ResidenciaResponseDTO;
import com.example.demo.service.ResidenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/residencias")
public class ResidenciaController {

    private final ResidenciaService service;

    public ResidenciaController(ResidenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResidenciaResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResidenciaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorIdDTO(id);
    }

    // converte o json para Residencia , salva no banco e retorna o objeto salvo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResidenciaResponseDTO salvarResidencia(@Valid @RequestBody ResidenciaRequestDTO r) {
        return service.salvar(r);
    }

}
