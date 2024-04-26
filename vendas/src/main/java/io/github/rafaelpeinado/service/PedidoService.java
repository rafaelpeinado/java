package io.github.rafaelpeinado.service;

import io.github.rafaelpeinado.domain.entity.Pedido;
import io.github.rafaelpeinado.domain.enums.StatusPedido;
import io.github.rafaelpeinado.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
