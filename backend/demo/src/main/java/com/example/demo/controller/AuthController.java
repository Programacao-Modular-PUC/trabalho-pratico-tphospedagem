package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ClienteService service;

    public AuthController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ClienteResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return service.autenticar(dto);
    }
}
