package br.com.alfac.food.api.pedido.dto;


public class ComboRequest {
    private LancheRequest lanche;
    private ItemRequest acompanhamento;
    private ItemRequest bebida;
    private ItemRequest sobremesa;

    public LancheRequest getLanche() {
        return lanche;
    }

    public void setLanche(LancheRequest lanche) {
        this.lanche = lanche;
    }

    public ItemRequest getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(ItemRequest acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public ItemRequest getBebida() {
        return bebida;
    }

    public void setBebida(ItemRequest bebida) {
        this.bebida = bebida;
    }

    public ItemRequest getSobremesa() {
        return sobremesa;
    }
    public void setSobremesa(ItemRequest sobremesa) {
        this.sobremesa = sobremesa;
    }

}