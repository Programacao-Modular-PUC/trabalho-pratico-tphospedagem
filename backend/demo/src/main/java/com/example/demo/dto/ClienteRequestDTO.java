package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
    @NotBlank(message = "Nome é obrigatório") String nome,
    @NotBlank(message = "CPF é obrigatório") String cpf,
    @NotBlank(message = "Endereço é obrigatório") String endereco,
    @NotBlank(message = "Telefone é obrigatório") String telefone,
    @Email(message = "Email inválido") String email
) {
}