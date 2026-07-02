package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.notificacao.NotificacaoInterna;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoInterna notificacaoInterna;

    public NotificacaoController(NotificacaoInterna notificacaoInterna) {
        this.notificacaoInterna = notificacaoInterna;
    }

    @GetMapping("/{clienteId}")
    public List<String> listarPorCliente(@PathVariable Long clienteId) {
        return notificacaoInterna.listarPorCliente(clienteId);
    }
}
