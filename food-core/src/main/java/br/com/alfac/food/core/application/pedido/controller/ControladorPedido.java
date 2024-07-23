package br.com.alfac.food.core.application.pedido.controller;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;
import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;
import br.com.alfac.food.core.application.pagamento.usecases.CriarPagamentoPendente;
import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.usecases.CriarPedido;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorPedido {

    private final CriarPedido criarPedido;
    private final CriarPagamentoPendente registrarPagamento;
    private final PagamentoClient pagamentoClient;
    private final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase;

    public ControladorPedido(final CriarPedido criarPedido, final CriarPagamentoPendente criarPagamentoPendente, final PagamentoClient pagamentoClient, final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase) {
        this.criarPedido = criarPedido;
        this.registrarPagamento = criarPagamentoPendente;
        this.pagamentoClient = pagamentoClient;
        this.consultarPagementoPorPedidoIdUseCase = consultarPagementoPorPedidoIdUseCase;
    }


    public PedidoCriadoDTO criarPedido(PedidoDTO pedidoDTO) throws FoodException {

        PedidoDTO pedido = criarPedido.executar(pedidoDTO);

        PagamentoEntityDTO pagamento = registrarPagamento.executar(pedido.getId());

        String qrCodeParaPagamento = pagamentoClient.gerarQrCode(pagamento.id());

        return new PedidoCriadoDTO(pedido, qrCodeParaPagamento);
    }

    public PagamentoEntityDTO consultarPagamentoPorPedidoId(Long pedidoId) throws FoodException {
        return consultarPagementoPorPedidoIdUseCase.executar(pedidoId);
    }
}
