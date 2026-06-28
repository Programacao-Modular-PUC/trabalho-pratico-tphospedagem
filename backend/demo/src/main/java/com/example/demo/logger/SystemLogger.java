package com.example.demo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemLogger {

    private static SystemLogger instance;
    private final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private SystemLogger() {}

    public static synchronized SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }

    public void log(String evento, String detalhe) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entrada = "[" + timestamp + "] " + evento + " - " + detalhe;
        logs.add(entrada);
        System.out.println(entrada);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void limpar() {
        logs.clear();
    }
}