package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
    @NotBlank(message = "Nome é obrigatório") String nome,
    @NotBlank(message = "CPF é obrigatório") String cpf,
    @NotBlank(message = "Endereço é obrigatório") String endereco,
    @NotBlank(message = "Telefone é obrigatório") String telefone,
    @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String senha
) {
}