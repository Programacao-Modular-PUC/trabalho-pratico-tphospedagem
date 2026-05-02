package com.example.demo.model;

import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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

    @OneToMany(mappedBy = "residencia")
    @JsonManagedReference
    private List<Quarto> quartos;

    
}
