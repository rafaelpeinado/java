package io.github.rafaelpeinado.domain.repositorio;

import io.github.rafaelpeinado.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    public List<Cliente> findByNomeLike(String nome);

}
