package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logger.SystemLogger;

@RestController
@RequestMapping("/logs")
public class LogController {

    @GetMapping
    public List<String> listar() {
        return SystemLogger.getInstance().getLogs();
    }

    @DeleteMapping
    public void limpar() {
        SystemLogger.getInstance().limpar();
    }
}
