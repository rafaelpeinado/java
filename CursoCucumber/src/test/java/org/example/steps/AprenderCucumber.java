package org.example.steps;

import io.cucumber.java.pt.Dados;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AprenderCucumber {
    @Dados("que criei o arquivo corretamente")
    public void que_criei_o_arquivo_corretamente() {
        System.out.println("passou aqui");
    }

    @Quando("executa-lo")
    public void executa_lo() {

    }

    @Então("a especificacao deve finalizar com sucesso")
    public void a_especificacao_deve_finalizar_com_sucesso() {

    }
}
