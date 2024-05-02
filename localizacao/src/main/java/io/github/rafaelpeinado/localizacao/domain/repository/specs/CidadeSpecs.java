package io.github.rafaelpeinado.localizacao.domain.repository.specs;

import io.github.rafaelpeinado.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    // opção mais genérica
    public static Specification<Cidade> propertyEqual(String prop, Object value) {
        return (root, query, cb) -> cb.equal(root.get(prop), value);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreaterThan(Long value) {
        return (root, query, cb) -> cb.greaterThan(root.get("habitantes"), value);
    }
}
