package com.example.demo.notificacao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Cliente;

// contexto do padrao Strategy: o Spring injeta automaticamente todos os
// beans que implementam CanalNotificacao nessa lista. se um canal novo
// for criado (ex: TelegramNotificacao), ele entra na lista sozinho,
// sem precisar alterar essa classe
@Service
public class NotificacaoService {

    private final List<CanalNotificacao> canais;
    private final SystemLogger logger = SystemLogger.getInstance();

    public NotificacaoService(List<CanalNotificacao> canais) {
        this.canais = canais;
    }

    public void notificar(Cliente cliente, String evento, String mensagem) {
        logger.log("NOTIFICACAO_DISPARADA",
            "Evento: " + evento + " | Cliente: " + cliente.getNome() + " | Canais ativos: " + canais.size());

        for (CanalNotificacao canal : canais) {
            canal.enviar(cliente, evento, mensagem);
        }
    }
}
