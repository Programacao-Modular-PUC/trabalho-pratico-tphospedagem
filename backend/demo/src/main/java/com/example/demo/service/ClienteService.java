package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listar(){
        return repository.findAll();
    }

    public Cliente salvar(Cliente c){
        return repository.save(c);
    }
}
