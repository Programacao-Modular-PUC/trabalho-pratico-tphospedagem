package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Quarto;
import com.example.demo.repository.QuartoRepository;
@Service


public class QuartoService {
    @Autowired
    private QuartoRepository repository;

    public List<Quarto> listar(){
        return repository.findAll();
    }

    public Quarto salvar(Quarto q){
        return repository.save(q);
    }
    
}
