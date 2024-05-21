package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;

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

    public List<Item> getItens() {
        List<Item> itens = new ArrayList<>();
        if (lanche != null) {
            itens.add(lanche);
        }
        if (acompanhamento != null) {
            itens.add(acompanhamento);
        }
        if (bebida != null) {
            itens.add(bebida);
        }
        if (sobremesa != null) {
            itens.add(sobremesa);
        }
        return itens;
    }

}