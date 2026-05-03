package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.example.demo.service.ClienteService;
import com.example.demo.model.Cliente;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar(){
        return service.listar();
    }
    
    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente c) {
        return service.salvar(c);
    }
    
}
