package org.example.models.entities;

public class Filme {
    private int estoque;
    private int aluguel;

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void setAluguel(int aluguel) {
        this.aluguel = aluguel;
    }

    public int getAluguel() {
        return this.aluguel;
    }

    public int getEstoque() {
        return this.estoque;
    }
}
