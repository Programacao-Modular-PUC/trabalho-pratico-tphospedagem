package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import com.example.demo.service.AluguelService;
import com.example.demo.model.Aluguel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    
    @Autowired
    private AluguelService service;

    @GetMapping
    public List<Aluguel> listar() {
        return service.listar();
    }

    @PostMapping
    public Aluguel salvarAluguel(@RequestBody Aluguel a) {
        
        
        return service.salvar(a);
    }
    
    
}
