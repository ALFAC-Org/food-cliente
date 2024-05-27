package br.com.alfac.food.core.domain.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.alfac.food.core.domain.base.AggregateRoot;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;
import br.com.alfac.food.core.utils.CollectionsUtils;

public class Pedido implements AggregateRoot {
    private Long id;
    private Cliente cliente;
    private StatusPedido status;
    private List<Combo> combos;
    private BigDecimal valorTotal;

    public void calcularValorTotal() {
        if (CollectionsUtils.naoVazio(combos)) {
            combos.forEach(Combo::calcularValorTotal);
            this.valorTotal = combos.stream()
                    .map(Combo::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    public void atualizarStatus() throws FoodException {

        if (StatusPedido.FINALIZADO.equals(this.status)) {
            throw new FoodException(PedidoErros.STATUS_PEDIDO_JA_FINALIZADO);
        }

        this.status = this.status.getProximoStatus();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

    public void adicionaCombo(Combo combo) {
        if(combos == null){
            combos = new ArrayList<>();
        }
        this.combos.add(combo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido statusPedido) {
        this.status = statusPedido;
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