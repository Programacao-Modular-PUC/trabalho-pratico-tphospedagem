package com.example.demo.notificacao;

import org.springframework.stereotype.Component;

import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Cliente;

@Component
public class SmsNotificacao implements CanalNotificacao {

    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void enviar(Cliente cliente, String evento, String mensagem) {
        String destino = cliente.getTelefone();

        System.out.println("[SMS] Para: " + destino + " | " + mensagem);

        logger.log("NOTIFICACAO_SMS",
            "Cliente: " + cliente.getNome() + " | Evento: " + evento + " | Destino: " + destino);
    }
}
