package org.example.services;

import org.example.models.entities.Filme;
import org.example.models.entities.NotaAluguel;
import org.example.utils.DateUtils;

public class AluguelService {

    public NotaAluguel alugar(Filme filme, String tipo) {
        if (filme.getEstoque() == 0) throw new RuntimeException("Filme sem estoque");
        NotaAluguel notaAluguel = new NotaAluguel();

        if("estendido".equals(tipo)) {
            notaAluguel.setPreco(filme.getAluguel() * 2);
            notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(3));
            notaAluguel.setPontuacao(2);
        } else {
            notaAluguel.setPreco(filme.getAluguel());
            notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
            notaAluguel.setPontuacao(1);
        }

        filme.setEstoque(filme.getEstoque() - 1);
        return notaAluguel;
    }
}
