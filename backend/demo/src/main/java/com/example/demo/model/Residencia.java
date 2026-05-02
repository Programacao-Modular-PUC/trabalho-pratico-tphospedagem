package com.example.demo.model;

import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Entity // diz que isso vira uma tabela
@Getter
@Setter
public class Residencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )



    private Long id;
    private String nome;
    private String endereco;
    private String bairro;
    private String telefone;
    private String imagem;

    
}
