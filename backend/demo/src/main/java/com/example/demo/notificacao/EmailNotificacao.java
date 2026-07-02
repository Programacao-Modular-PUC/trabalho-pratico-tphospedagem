package com.example.demo.notificacao;

import org.springframework.stereotype.Component;

import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Cliente;

@Component
public class EmailNotificacao implements CanalNotificacao {

    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void enviar(Cliente cliente, String evento, String mensagem) {
        String destino = cliente.getEmail();

        // aqui simulamos o envio (não temos servidor SMTP configurado)
        System.out.println("[EMAIL] Para: " + destino + " | " + mensagem);

        logger.log("NOTIFICACAO_EMAIL",
            "Cliente: " + cliente.getNome() + " | Evento: " + evento + " | Destino: " + destino);
    }
}
