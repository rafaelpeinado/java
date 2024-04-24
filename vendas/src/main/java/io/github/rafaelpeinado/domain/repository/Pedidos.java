package io.github.rafaelpeinado.domain.repository;

import io.github.rafaelpeinado.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
