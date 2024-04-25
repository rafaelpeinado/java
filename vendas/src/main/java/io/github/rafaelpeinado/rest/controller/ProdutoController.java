package io.github.rafaelpeinado.rest.controller;

import io.github.rafaelpeinado.domain.entity.Produto;
import io.github.rafaelpeinado.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        repository.findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return p;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }
}
