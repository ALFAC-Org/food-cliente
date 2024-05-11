package br.com.alfac.food.core.domain.pedido;

import br.com.alfac.food.core.domain.item.Item;

public class Combo {
    private Lanche lanche;
    private Item acompanhamento;
    private Item bebida;
    private Item sobremesa;

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public Item getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Item acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Item getBebida() {
        return bebida;
    }

    public void setBebida(Item bebida) {
        this.bebida = bebida;
    }

    public Item getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(Item sobremesa) {
        this.sobremesa = sobremesa;
    }


}