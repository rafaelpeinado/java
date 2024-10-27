package org.example.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


    Date entrega = new Date();

    @Dado("que a entrega é dia {int}\\/{int}\\/{int}")
    public void queAEntregaÉDia(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.YEAR, ano);
        entrega = calendar.getTime();
    }

    @Quando("a entrega atrasar em {int} {string}")
    public void aEntregaAtrasarEmDias(int qtde, String tempo) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entrega);
        switch (tempo) {
            case "dias":
            case "dia":
                calendar.add(Calendar.DAY_OF_MONTH, qtde);
                break;
            case "meses":
            case "mes":
                calendar.add(Calendar.MONTH, qtde);
                break;
            default:
                throw new Exception("Tempo inválido");
        }
        entrega = calendar.getTime();
    }

    @Então("a entrega será efetuada em {string}")
    public void aEntregaSeráEfetuadaEm(String data) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = format.format(entrega);

        assertEquals(data, dataFormatada);
    }


    @Dado("^que o ticket( especial)? é (A.\\d{3})$")
    public void queOTicketÉ(String tipo, String ticket) {

    }

    @Dado("^que o valor da passagem é R\\$ (\\d+),(\\d+)$")
    public void queOValorDaPassagemÉR$(int reais, int centavos) {

    }

    @Dado("^que o nome do passageiro é \"(.{5,20})\"$")
    public void queONomeDoPassageiroÉ(String nome) {

    }

    @Dado("^que o telefone do passageiro é (9\\d{3}-\\d{4})$")
    public void queOTelefoneDoPassageiroÉ(String telefone) {

    }

    @Quando("criar os steps")
    public void criarOsSteps() {

    }

    @Então("o teste vai funcionar")
    public void oTesteVaiFuncionar() {

    }
}


