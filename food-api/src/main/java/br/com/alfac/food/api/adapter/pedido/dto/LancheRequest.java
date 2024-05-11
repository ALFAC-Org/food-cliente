package br.com.alfac.food.api.adapter.pedido.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.ItemMenuDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;

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

    public LancheDTO toLancheDTO() {

        LancheDTO lancheDTO = new LancheDTO();
        List<ItemMenuDTO> complementosDTO = new ArrayList<>();

        if (complementos != null) {
            for (ItemRequest complemento : complementos) {
                complementosDTO.add(complemento.toDTO());
            }
        }

        lancheDTO.setId(getId());
        lancheDTO.setObservacoes(observacoes);
        lancheDTO.setComplementos(complementosDTO);

        return lancheDTO;
    }

}