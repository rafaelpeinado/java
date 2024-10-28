package org.example.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.example.models.entities.Filme;
import org.example.models.entities.NotaAluguel;
import org.example.services.AluguelService;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguelService = new AluguelService();
    private NotaAluguel notaAluguel;
    private String erro;

    @Dado("um filme com estoque de {int} unidades")
    public void umFilmeComEstoqueDeUnidades(int unidades) {
        filme = new Filme();
        filme.setEstoque(unidades);
    }

    @Dado("que o preço de aluguel seja R$ {int}")
    public void queOPreçoDeAluguelSejaR$(int preco) {
        filme.setAluguel(preco);
    }

    @Quando("alugar")
    public void alugar() {
        try {
            notaAluguel = aluguelService.alugar(filme);
        } catch (RuntimeException e) {
            erro = e.getMessage();
        }

    }

    @Então("o preço do aluguel será R$ {int}")
    public void oPreçoDoAluguelSeráR$(int preco) {
        assertEquals(preco, notaAluguel.getPreco());
    }

    @Então("a data de entrega será no dia seguinte")
    public void aDataDeEntregaSeráNoDiaSeguinte() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        Date dataRetorno = notaAluguel.getDataEntrega();
        Calendar calRetorno = Calendar.getInstance();
        calRetorno.setTime(dataRetorno);

        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calRetorno.get(Calendar.DAY_OF_MONTH));
        assertEquals(calendar.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
        assertEquals(calendar.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR));
    }

    @Então("o estoque do filme será {int} unidade")
    public void oEstoqueDoFilmeSeráUnidade(int unidades) {
        assertEquals(unidades, filme.getEstoque());
    }

    @Então("não será possível por falta de estoque")
    public void nãoSeráPossívelPorFaltaDeEstoque() {
        assertEquals("Filme sem estoque", erro);
    }
}
