package com.example.demo.dto;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String cpf,
    String endereco,
    String telefone,
    String email
) {
}