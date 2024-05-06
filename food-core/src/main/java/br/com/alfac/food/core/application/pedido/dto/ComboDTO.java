package br.com.alfac.food.core.application.pedido.dto;


public class ComboDTO {
    private LancheDTO lanche;
    private AcompanhamentoDTO acompanhamento;
    private BebidaDTO bebida;
    private SobremesaDTO sobremesa;

    public LancheDTO getLanche() {
        return lanche;
    }

    public void setLanche(LancheDTO lanche) {
        this.lanche = lanche;
    }

    public AcompanhamentoDTO getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AcompanhamentoDTO acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public BebidaDTO getBebida() {
        return bebida;
    }

    public void setBebida(BebidaDTO bebida) {
        this.bebida = bebida;
    }

    public SobremesaDTO getSobremesa() {
        return sobremesa;
    }
    public void setSobremesa(SobremesaDTO sobremesa) {
        this.sobremesa = sobremesa;
    }

}