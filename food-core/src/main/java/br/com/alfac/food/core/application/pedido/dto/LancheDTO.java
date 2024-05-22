package br.com.alfac.food.core.application.pedido.dto;

import java.util.List;

import br.com.alfac.food.core.application.item.dto.ItemDTO;

public class LancheDTO extends ItemDTO {
    private List<ItemDTO> complementos;
    private String observacoes;

    public List<ItemDTO> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ItemDTO> complementos) {
        this.complementos = complementos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
}