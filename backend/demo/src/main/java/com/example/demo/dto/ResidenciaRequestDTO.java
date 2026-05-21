package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record ResidenciaRequestDTO(
    @NotBlank(message = "Nome da residência é obrigatório") String nome,
    @NotBlank(message = "Endereço é obrigatório") String endereco,
    @NotBlank(message = "Bairro é obrigatório") String bairro,
    @NotBlank(message = "Telefone é obrigatório") String telefone,
    String imagem
) {
}