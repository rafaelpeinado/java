package io.github.rafaelpeinado.service;

import io.github.rafaelpeinado.domain.entity.Pedido;
import io.github.rafaelpeinado.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
