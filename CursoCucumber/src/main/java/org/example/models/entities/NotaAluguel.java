package org.example.models.entities;

import io.cucumber.java.zh_cn.假如;

import java.util.Date;

public class NotaAluguel {
    private int preco;
    private Date dataEntrega;
    private int pontuacao;

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Date getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Date time) {
        this.dataEntrega = time;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
