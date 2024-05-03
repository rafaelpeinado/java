package io.github.rafaelpeinado.localizacao.service;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import io.github.rafaelpeinado.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.rafaelpeinado.localizacao.domain.repository.specs.CidadeSpecs.*;

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

    public void listarCidadesPorNomeSQL() {
//        repository.findByNomeSqlNativo("São Paulo").forEach(System.out::println);
        repository.findByNomeSqlNativo("São Paulo")
                .stream()
                .map(cidadeProjection ->
                        new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
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

    public void listarCidadesByNomeSpec() {
//        Specification<Cidade> spec = CidadeSpecs.nomeEqual("São Paulo");
//        repository.findAll(spec).forEach(System.out::println);
        repository
                .findAll(nomeEqual("São Paulo").or(habitantesGreaterThan(1000L)))
                .forEach(System.out::println);
    }

    public void listarCidadesByGenericSpec() {
        repository
                .findAll(propertyEqual("nome", "São Paulo").and(propertyEqual("habitantes", 12396372L)))
                .forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
        // select * from cidade where 1 = 1 (conjunction)
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());
        if (filtro.getId() != null) {
            specs = specs.and(idEqual(filtro.getId()));
        }
        if (StringUtils.hasText(filtro.getNome())) {
            specs = specs.and(nomeLike(filtro.getNome()));
        }
        if (filtro.getHabitantes() != null) {
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }
}
