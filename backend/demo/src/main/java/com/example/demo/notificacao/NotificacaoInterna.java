package com.example.demo.notificacao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.example.demo.logger.SystemLogger;
import com.example.demo.model.Cliente;

// esse canal não "envia" pra fora, ele guarda a mensagem numa caixa
// interna do cliente, tipo um sino de notificação dentro do sistema
@Component
public class NotificacaoInterna implements CanalNotificacao {

    private final Map<Long, List<String>> caixaPorCliente = new ConcurrentHashMap<>();
    private final SystemLogger logger = SystemLogger.getInstance();

    @Override
    public void enviar(Cliente cliente, String evento, String mensagem) {
        caixaPorCliente
            .computeIfAbsent(cliente.getId(), id -> new CopyOnWriteArrayList<>())
            .add(mensagem);

        logger.log("NOTIFICACAO_INTERNA",
            "Cliente: " + cliente.getNome() + " | Evento: " + evento);
    }

    public List<String> listarPorCliente(Long clienteId) {
        return caixaPorCliente.getOrDefault(clienteId, List.of());
    }
}
