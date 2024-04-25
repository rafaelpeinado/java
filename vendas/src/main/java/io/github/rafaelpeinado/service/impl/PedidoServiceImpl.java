package io.github.rafaelpeinado.service.impl;

import io.github.rafaelpeinado.domain.repository.Pedidos;
import io.github.rafaelpeinado.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
