package io.github.rafaelpeinado.localizacao.domain.repository.specs;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    // opção mais genérica
    public static Specification<Cidade> propertyEqual(String prop, Object value) {
        return (root, query, cb) -> cb.equal(root.get(prop), value);
    }

    public static Specification<Cidade> idEqual(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome + "%".toUpperCase());
    }

    public static Specification<Cidade> habitantesGreaterThan(Long value) {
        return (root, query, cb) -> cb.greaterThan(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max) {
        return (root, query, cb) -> cb.between(root.get("habitantes"), min, max);
    }
}
