package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Quarto;
import com.example.demo.service.QuartoService;

@RestController
@RequestMapping("/quartos")
@CrossOrigin(origins = "*")
public class QuartoController {

    @Autowired
    private QuartoService service;

    @GetMapping
    public List<Quarto> listar() {
        return service.listar();
    }

    @PostMapping
    public Quarto salvar(@RequestBody Quarto q) {
        return service.salvar(q);
    }

    @GetMapping("/diaria/{id}")
    public double calcularDiaria(@PathVariable Long id,
                                  @RequestParam(defaultValue = "false") boolean berco) {
        Quarto q = service.listar().stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElseThrow();
        return service.calcularDiaria(q, berco);
    }
}