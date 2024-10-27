package org.example.services;

import org.example.models.entities.Filme;
import org.example.models.entities.NotaAluguel;

import java.util.Calendar;

public class AluguelService {

    public NotaAluguel alugar(Filme filme) {
        NotaAluguel notaAluguel = new NotaAluguel();
        notaAluguel.setPreco(filme.getAluguel());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        notaAluguel.setDataEntrega(calendar.getTime());
        filme.setEstoque(filme.getEstoque() - 1);
        return notaAluguel;
    }
}
