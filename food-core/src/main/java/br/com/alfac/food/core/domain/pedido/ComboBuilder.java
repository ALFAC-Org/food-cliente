package br.com.alfac.food.core.domain.pedido;

import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;

public final class ComboBuilder {
    private Combo combo;

    private ComboBuilder() {
        combo = new Combo();
    }

    public static ComboBuilder combo() {
        return new ComboBuilder();
    }

    public ComboBuilder comLanche(Lanche lanche) {
        combo.setLanche(lanche);
        return this;
    }

    public ComboBuilder comAcompanhamento(Item acompanhamento) {
        combo.setAcompanhamento(acompanhamento);
        return this;
    }

    public ComboBuilder comBebida(Item bebida) {
        combo.setBebida(bebida);
        return this;
    }

    public ComboBuilder comSobremesa(Item sobremesa) {
        combo.setSobremesa(sobremesa);
        return this;
    }

    public Combo build() throws FoodException {
        combo.calcularValorTotal();
        combo.validarItens();
        return combo;
    }
}
