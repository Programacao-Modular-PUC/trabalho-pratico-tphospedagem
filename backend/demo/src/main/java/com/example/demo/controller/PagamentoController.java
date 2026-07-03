package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.PagamentoRequestDTO;
import com.example.demo.dto.PagamentoResponseDTO;
import com.example.demo.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public PagamentoResponseDTO processar(@RequestBody PagamentoRequestDTO dto) {
        return service.processar(dto);
    }
}