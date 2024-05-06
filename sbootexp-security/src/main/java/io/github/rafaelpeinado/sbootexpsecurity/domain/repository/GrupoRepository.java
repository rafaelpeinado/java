package io.github.rafaelpeinado.sbootexpsecurity.domain.repository;

import io.github.rafaelpeinado.sbootexpsecurity.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, String> {
}
