package org.example.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.Assert.assertEquals;

public class AprenderCucumber {
    @Dado("que criei o arquivo corretamente")
    public void que_criei_o_arquivo_corretamente() {
        System.out.println("passou aqui");
    }

    @Quando("executa-lo")
    public void executa_lo() {

    }

    @Então("a especificacao deve finalizar com sucesso")
    public void a_especificacao_deve_finalizar_com_sucesso() {

    }


    private int contador = 0;
    @Dado("que o valor do contador é {int}")
    public void queOValorDoContadorÉ(int value) {
        contador = value;
    }

    @Quando("eu incrementar em {int}")
    public void euIncrementarEm(int value) {
        contador += value;
    }

    @Então("o valor do contador será {int}")
    public void oValorDoContadorSerá(int value) {
        assertEquals(value, contador);
    }
}


