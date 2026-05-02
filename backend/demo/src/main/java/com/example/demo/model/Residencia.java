package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Entity // diz que isso vira uma tabela
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
