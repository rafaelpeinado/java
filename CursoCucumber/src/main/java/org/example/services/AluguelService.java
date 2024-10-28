package org.example.services;

import org.example.models.entities.Filme;
import org.example.models.entities.NotaAluguel;
import org.example.utils.DateUtils;

public class AluguelService {

    public NotaAluguel alugar(Filme filme) {
        if (filme.getEstoque() == 0) throw new RuntimeException("Filme sem estoque");
        NotaAluguel notaAluguel = new NotaAluguel();

        notaAluguel.setPreco(filme.getAluguel());
        notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
        filme.setEstoque(filme.getEstoque() - 1);
        return notaAluguel;
    }
}
