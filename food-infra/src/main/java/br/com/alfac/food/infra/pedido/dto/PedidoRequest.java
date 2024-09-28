package br.com.alfac.food.infra.pedido.dto;

import java.util.List;

public class PedidoRequest {
    private List<ComboRequest> combos;

    public List<ComboRequest> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboRequest> combos) {
        this.combos = combos;
    }

}