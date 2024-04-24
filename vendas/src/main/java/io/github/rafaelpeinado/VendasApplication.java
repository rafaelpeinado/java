package io.github.rafaelpeinado;

import io.github.rafaelpeinado.domain.entity.Cliente;
import io.github.rafaelpeinado.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Rafael"));
            clientes.save(new Cliente("Outro Cliente"));

            boolean existe = clientes.existsByNome("Rafael");
            System.out.println("existe um cliente com o nome Rafael? " + existe);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
