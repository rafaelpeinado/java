package org.example.models.entities;

import java.util.Date;

public class NotaAluguel {
    private int preco;
    private Date dataEntrega;

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
}
