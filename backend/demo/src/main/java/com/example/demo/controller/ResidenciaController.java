package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demo.service.ResidenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.Residencia;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/residencias")
public class ResidenciaController {

    @Autowired
    private ResidenciaService service;

    @GetMapping
    public List<Residencia> listar(){
        return service.listar();
    }


    // converte o json para Residencia , salva no banco e retorna o objeto salvo
    @PostMapping
    public Residencia salvarResidencia(@RequestBody Residencia r) {
    
        return service.salvar(r);
    }
    


}
