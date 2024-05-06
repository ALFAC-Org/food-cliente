package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

public class LancheDTO {
    private int itemId;
    private List<ComplementoDTO> complementos;
    private String observacoes;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public List<ComplementoDTO> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ComplementoDTO> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
}