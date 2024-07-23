package br.com.alfac.food.core.domain.pagamento;

import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;

import java.time.LocalDateTime;
import java.util.Objects;

public class Pagamento {

    private Long id;
    private StatusPagamento statusPagamento;
    private Long pedidoId;
    private LocalDateTime dataPagamento;

    public Pagamento(final Long pedidoId) throws FoodException {

        if (Objects.isNull(pedidoId)) {
            throw new FoodException(PagamentoErro.PEDIDO_ID_OBRIGATORIO);
        }

        this.pedidoId = pedidoId;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.dataPagamento = LocalDateTime.now();
    }

    public Pagamento(final Long id, final StatusPagamento statusPagamento, final Long pedidoId, final LocalDateTime dataPagamento) throws FoodException {
        validarDados(id, statusPagamento, pedidoId, dataPagamento);
        this.id = id;
        this.statusPagamento = statusPagamento;
        this.pedidoId = pedidoId;
        this.dataPagamento = dataPagamento;
    }

    private void validarDados(final Long id, final StatusPagamento statusPagamento, final Long pedidoId, final LocalDateTime dataPagamento) throws FoodException {
        if (Objects.isNull(id)) {
            throw new FoodException(PagamentoErro.ID_OBRIGATORIO);
        }

        if (Objects.isNull(statusPagamento)) {
            throw new FoodException(PagamentoErro.STATUS_OBRIGATORIO);
        }

        if (Objects.isNull(pedidoId)) {
            throw new FoodException(PagamentoErro.PEDIDO_ID_OBRIGATORIO);
        }

        if (Objects.isNull(dataPagamento)) {
            throw new FoodException(PagamentoErro.DATA_PAGAMENTO_OBRIGATORIO);
        }
    }

    public void aprovar() throws FoodException {

        if (!StatusPagamento.PENDENTE.equals(this.statusPagamento)) {
            throw new FoodException(PagamentoErro.STATUS_INVALIDO_APROVACAO);
        }

        this.statusPagamento = StatusPagamento.APROVADO;
    }

    public Long getId() {
        return id;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
}
