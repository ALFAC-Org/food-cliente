package br.com.alfac.food.core.application.pedido.dto;

import br.com.alfac.food.core.application.item.dto.ItemDTO;

public class ComboDTO {
    private LancheDTO lanche;
    private ItemDTO acompanhamento;
    private ItemDTO bebida;
    private ItemDTO sobremesa;

    public LancheDTO getLanche() {
        return lanche;
    }

    public void setLanche(LancheDTO lanche) {
        this.lanche = lanche;
    }

    public ItemDTO getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(ItemDTO acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public ItemDTO getBebida() {
        return bebida;
    }

    public void setBebida(ItemDTO bebida) {
        this.bebida = bebida;
    }

    public ItemDTO getSobremesa() {
        return sobremesa;
    }
    public void setSobremesa(ItemDTO sobremesa) {
        this.sobremesa = sobremesa;
    }

}