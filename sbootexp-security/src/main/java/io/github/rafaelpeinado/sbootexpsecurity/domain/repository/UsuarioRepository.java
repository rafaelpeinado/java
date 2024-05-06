package io.github.rafaelpeinado.sbootexpsecurity.domain.repository;

import io.github.rafaelpeinado.sbootexpsecurity.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
