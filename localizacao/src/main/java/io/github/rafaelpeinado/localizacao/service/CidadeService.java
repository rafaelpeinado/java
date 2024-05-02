package io.github.rafaelpeinado.localizacao.service;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import io.github.rafaelpeinado.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public void listarCidadesPorNome() {
        Pageable pageable = PageRequest.of(0, 2);
//        cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
//        cidadeRepository.findByNomeStartingWith("Porto").forEach(System.out::println);
//        cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
//        cidadeRepository.findByNomeContaining("a").forEach(System.out::println);
//        cidadeRepository.findByNomeLike("%a%").forEach(System.out::println);
        repository.findByNomeLike("porto%", Sort.by("habitantes", "nome")).forEach(System.out::println);
        repository.findByNomeLike("porto%", pageable).forEach(System.out::println);
        repository.findByNomeLike("%%%%", pageable).forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
//        cidadeRepository.findByHabitantes(78787900L).forEach(System.out::println);
        repository.findByHabitantesLessThan(1000001L).forEach(System.out::println);
        repository.findByHabitantesGreaterThan(1000001L).forEach(System.out::println);
        repository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);
        repository.findByHabitantesLessThanAndNomeLike(1000001L, "Bra%").forEach(System.out::println);
    }

    @Transactional
    public void salvarCidade() {
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        repository.save(cidade);
    }

    public void listarCidades() {
        repository.findAll()
                .forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase("nome")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);

        return repository.findAll(example);
    }
}
