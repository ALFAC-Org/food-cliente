package br.com.alfac.food.core.application.pagamento.adapters.controller;

import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pagamento.usecases.AlterarStatusPagamentoRealizadoUseCase;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.application.pedido.usecases.AtualizarStatusPedidoPagamentoProcessadoUseCase;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.domain.pagamento.StatusConfirmacaoPagamento;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorRecebimentoPagamento {


    private final AlterarStatusPagamentoRealizadoUseCase alterarStatusPagamentoRealizadoUseCase;
    private final AtualizarStatusPedidoPagamentoProcessadoUseCase atualizarStatusPedidoPagamentoProcessadoUseCase;

    public ControladorRecebimentoPagamento(final RepositorioPedidoGateway pedidoRepository, final RepositorioPagamentoGateway pagamentoRepository) {
        this.alterarStatusPagamentoRealizadoUseCase = new AlterarStatusPagamentoRealizadoUseCase(pagamentoRepository);
        this.atualizarStatusPedidoPagamentoProcessadoUseCase = new AtualizarStatusPedidoPagamentoProcessadoUseCase(pedidoRepository);
    }


    public void executarRetornoPagamento(final Long idPagamento, final StatusConfirmacaoPagamento statusConfirmacaoPagamento) throws FoodException {


        Pagamento pagamento = this.alterarStatusPagamentoRealizadoUseCase.executar(idPagamento, statusConfirmacaoPagamento);

        atualizarStatusPedidoPagamentoProcessadoUseCase.executar(pagamento.getPedidoId(), pagamento.getStatusPagamento());

    }
}
