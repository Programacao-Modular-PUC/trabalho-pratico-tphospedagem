package com.example.demo.dto;

public record ResidenciaResponseDTO(
    Long id,
    String nome,
    String endereco,
    String bairro,
    String telefone,
    String imagem,
    int quantidadeQuartos
) {
}