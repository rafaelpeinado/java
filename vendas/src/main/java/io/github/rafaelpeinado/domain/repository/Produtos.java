package io.github.rafaelpeinado.domain.repository;

import io.github.rafaelpeinado.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
