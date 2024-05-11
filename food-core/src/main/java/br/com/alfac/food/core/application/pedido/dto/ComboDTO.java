package br.com.alfac.food.core.application.pedido.dto;


public class ComboDTO {
    private LancheDTO lanche;
    private ItemMenuDTO acompanhamento;
    private ItemMenuDTO bebida;
    private ItemMenuDTO sobremesa;

    public LancheDTO getLanche() {
        return lanche;
    }

    public void setLanche(LancheDTO lanche) {
        this.lanche = lanche;
    }

    public ItemMenuDTO getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(ItemMenuDTO acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public ItemMenuDTO getBebida() {
        return bebida;
    }

    public void setBebida(ItemMenuDTO bebida) {
        this.bebida = bebida;
    }

    public ItemMenuDTO getSobremesa() {
        return sobremesa;
    }
    public void setSobremesa(ItemMenuDTO sobremesa) {
        this.sobremesa = sobremesa;
    }

}