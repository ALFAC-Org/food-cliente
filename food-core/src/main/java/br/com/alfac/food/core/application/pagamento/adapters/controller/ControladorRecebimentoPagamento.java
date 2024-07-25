package br.com.alfac.food.core.application.pagamento.adapters.controller;

import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pagamento.usecases.AlterarStatusPagamentoRealizadoUserCase;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.application.pedido.usecases.AtualizarStatusPedidoPagamentoRecebidoUserCase;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorRecebimentoPagamento {


    private final AlterarStatusPagamentoRealizadoUserCase alterarStatusPagamentoRealizadoUserCase;
    private final AtualizarStatusPedidoPagamentoRecebidoUserCase atualizarStatusPedidoPagamentoRecebidoUserCase;

    public ControladorRecebimentoPagamento(final RepositorioPedidoGateway pedidoRepository, final RepositorioPagamentoGateway pagamentoRepository) {
        this.alterarStatusPagamentoRealizadoUserCase = new AlterarStatusPagamentoRealizadoUserCase(pagamentoRepository);
        this.atualizarStatusPedidoPagamentoRecebidoUserCase = new AtualizarStatusPedidoPagamentoRecebidoUserCase(pedidoRepository);
    }


    public void executar(final Long idPagamento) throws FoodException {

        Pagamento pagamento = this.alterarStatusPagamentoRealizadoUserCase.executar(idPagamento);

        atualizarStatusPedidoPagamentoRecebidoUserCase.executar(pagamento.getPedidoId());

    }
}
