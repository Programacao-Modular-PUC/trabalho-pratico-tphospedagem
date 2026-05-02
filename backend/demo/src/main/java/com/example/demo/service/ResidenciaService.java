package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Residencia;
import com.example.demo.repository.ResidenciaRepository;

@Service // essa classe faz parte da loogica do sistema



public class ResidenciaService {
    @Autowired // injetar dependências (objetos) dentro da classe -> "me entrega um repositorio pronto pra usar"
    private ResidenciaRepository repository;
    
    public List<Residencia> listar(){
        return repository.findAll();
    }

    public Residencia salvar(Residencia r){
        return repository.save(r);
    }
}
