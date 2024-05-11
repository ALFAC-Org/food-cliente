package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

public class LancheDTO extends ItemMenuDTO {
    private List<ItemMenuDTO> complementos;
    private String observacoes;

    public List<ItemMenuDTO> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ItemMenuDTO> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
}