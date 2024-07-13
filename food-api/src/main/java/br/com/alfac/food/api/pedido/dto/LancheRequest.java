package br.com.alfac.food.api.pedido.dto;

import java.util.List;

public class LancheRequest extends ItemRequest {
    private List<ItemRequest> complementos;
    private String observacoes;

    public List<ItemRequest> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ItemRequest> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}