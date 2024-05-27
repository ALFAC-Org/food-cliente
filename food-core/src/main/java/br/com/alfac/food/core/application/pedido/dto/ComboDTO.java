package br.com.alfac.food.core.application.pedido.dto;


import br.com.alfac.food.core.application.item.dto.ItemDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ComboDTO {
    private LancheDTO lanche;
    private ItemDTO acompanhamento;
    private ItemDTO bebida;
    private ItemDTO sobremesa;
    private BigDecimal valorTotal;

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

    public BigDecimal getValorTotal() {
        if (Objects.nonNull(valorTotal)) {
            return valorTotal.setScale(2, RoundingMode.HALF_UP);
        }

        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}