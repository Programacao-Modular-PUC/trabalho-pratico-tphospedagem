package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Aluguel;
import com.example.demo.repository.AluguelRepository;


@Service
public class AluguelService {

    @Autowired
    private AluguelRepository repository;

    public List<Aluguel> listar(){
        return repository.findAll();
    }

    public Aluguel salvar(Aluguel a ){
        return repository.save(a);

    }


    
}
