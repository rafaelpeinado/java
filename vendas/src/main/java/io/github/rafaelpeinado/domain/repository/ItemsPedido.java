package io.github.rafaelpeinado.domain.repository;

import io.github.rafaelpeinado.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
