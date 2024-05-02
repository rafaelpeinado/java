package io.github.rafaelpeinado.localizacao.domain.repository;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
