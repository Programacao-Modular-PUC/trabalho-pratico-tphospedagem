package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demo.service.QuartoService;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Quarto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/quartos")
public class QuartoController {
    

    @Autowired
    private QuartoService service;

    @GetMapping
    public List<Quarto> listar(){
        return service.listar();
    }

    @PostMapping
    public Quarto salvarQuarto(@RequestBody Quarto q) {
        return service.salvar(q);
    }
    
    

}
