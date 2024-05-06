package br.com.alfac.food.core.domain.pedido;

import java.util.Optional;

public class Combo {
    private Optional<Lanche> lanche;
    private Optional<Acompanhamento> acompanhamento;
    private Optional<Bebida> bebida;
    private Optional<Sobremesa> sobremesa;

    public Optional<Lanche> getLanche() {
        return lanche;
    }

    public void setLanche(Optional<Lanche> lanche) {
        this.lanche = lanche;
    }

    public Optional<Acompanhamento> getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Optional<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Optional<Bebida> getBebida() {
        return bebida;
    }

    public void setBebida(Optional<Bebida> bebida) {
        this.bebida = bebida;
    }

    public Optional<Sobremesa> getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(Optional<Sobremesa> sobremesa) {
        this.sobremesa = sobremesa;
    }

}