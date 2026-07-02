package com.example.demo.notificacao;

import org.springframework.stereotype.Component;

import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Cliente;

@Component
public class WhatsappNotificacao implements CanalNotificacao {

    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void enviar(Cliente cliente, String evento, String mensagem) {
        String destino = cliente.getTelefone();

        System.out.println("[WHATSAPP] Para: " + destino + " | " + mensagem);

        logger.log("NOTIFICACAO_WHATSAPP",
            "Cliente: " + cliente.getNome() + " | Evento: " + evento + " | Destino: " + destino);
    }
}
