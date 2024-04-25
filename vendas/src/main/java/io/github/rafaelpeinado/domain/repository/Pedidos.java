package io.github.rafaelpeinado.domain.repository;

import io.github.rafaelpeinado.domain.entity.Cliente;
import io.github.rafaelpeinado.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
