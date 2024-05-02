package io.github.rafaelpeinado.localizacao;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import io.github.rafaelpeinado.localizacao.domain.repository.CidadeRepository;
import io.github.rafaelpeinado.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

    @Autowired
    private CidadeService service;

    @Override
    public void run(String... args) throws Exception {
        service.listarCidadesPorHabitantes();
        service.listarCidadesPorNome();

        var cidade = new Cidade(null, "porto", null);
        service.filtroDinamico(cidade).forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(LocalizacaoApplication.class, args);
    }

}
