package com.example.demo.notificacao;

import com.example.demo.model.Cliente;

// Strategy: cada canal implementa essa interface do seu jeito.
// Pra adicionar um canal novo (ex: Telegram) basta criar uma classe
// nova implementando essa interface e marcar com @Component,
// sem precisar mexer no NotificacaoService.
public interface CanalNotificacao {

    void enviar(Cliente cliente, String evento, String mensagem);
}
