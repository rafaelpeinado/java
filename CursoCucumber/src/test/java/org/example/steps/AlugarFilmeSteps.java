package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.example.models.entities.Filme;
import org.example.models.entities.NotaAluguel;
import org.example.models.enums.TipoAluguel;
import org.example.services.AluguelService;
import org.example.utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguelService = new AluguelService();
    private NotaAluguel notaAluguel;
    private String erro;
    private TipoAluguel tipoAluguel;

    @Dado("um filme com estoque de {int} unidades")
    public void umFilmeComEstoqueDeUnidades(int unidades) {
        filme = new Filme();
        filme.setEstoque(unidades);
    }

    @Dado("que o preço de aluguel seja R$ {int}")
    public void queOPreçoDeAluguelSejaR$(int preco) {
        filme.setAluguel(preco);
    }

    @Dado("um filme")
    public void umFilme(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        filme = new Filme();
        filme.setEstoque(Integer.parseInt(map.get("estoque")));
        filme.setAluguel(Integer.parseInt(map.get("preco")));
        String tipo = map.get("tipo");
        tipoAluguel = tipo.equals("semanal")
                ? TipoAluguel.SEMANAL : tipo.equals("estendido")
                ? TipoAluguel.ESTENDIDO : TipoAluguel.COMUM;
    }

    @Quando("alugar")
    public void alugar() {
        try {
            notaAluguel = aluguelService.alugar(filme, tipoAluguel);
        } catch (RuntimeException e) {
            erro = e.getMessage();
        }

    }

    @Então("o preço do aluguel será R$ {int}")
    public void oPreçoDoAluguelSeráR$(int preco) {
        assertEquals(preco, notaAluguel.getPreco());
    }

    @Então("o estoque do filme será {int} unidade")
    public void oEstoqueDoFilmeSeráUnidade(int unidades) {
        assertEquals(unidades, filme.getEstoque());
    }

    @Então("não será possível por falta de estoque")
    public void nãoSeráPossívelPorFaltaDeEstoque() {
        assertEquals("Filme sem estoque", erro);
    }

    @Dado("que o tipo de aluguel seja {string}")
    public void queOTipoDeAluguelSejaEstendido(String tipo) {
        tipoAluguel = tipo.equals("semanal")
                ? TipoAluguel.SEMANAL : tipo.equals("estendido")
                ? TipoAluguel.ESTENDIDO : TipoAluguel.COMUM;
    }

    @Então("^a data de entrega será em (\\d+) dias?$")
    public void aDataDeEntregaSeráEmDias(int dias) {
        Date dataEsperada = DateUtils.obterDataDiferencaDias(dias);
        Date dataReal = notaAluguel.getDataEntrega();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        assertEquals(format.format(dataEsperada), format.format(dataReal));
    }

    @Então("^a pontuação será de (\\d+) pontos?$")
    public void aPontuaçãoSeráDePontos(int pontos) {
        assertEquals(pontos, notaAluguel.getPontuacao());
    }
}
