package br.com.alfac.food.core.application.pagamento.controller;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoService;
import br.com.alfac.food.core.application.pedido.gateways.StatusPedidoPagamentoService;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;

public class ControladorRegistraPagamento {


//    private final PagamentoClient pagamentoClient;
//    private final PedidoService pedidoService;
//    private final StatusPedidoPagamentoService statusPedidoPagamentoService;
//
//    public RegistrarPagamento(final PagamentoClient pagamentoClient, final PedidoService pedidoService,
//                              final StatusPedidoPagamentoService statusPedidoPagamentoService) {
//        this.pagamentoClient = pagamentoClient;
//        this.pedidoService = pedidoService;
//        this.statusPedidoPagamentoService = statusPedidoPagamentoService;
//    }
//
//
//    @Override
//    public PagamentoDTO efetuarPagamento(final Long idPedido) throws FoodException {
//
//        PedidoDTO pedidoDTO = pedidoService.consultarPedidoPorId(idPedido);
//
//        if (!StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedidoDTO.getStatusPedido())) {
//            throw new FoodException(PagamentoErro.PAGAMENTO_JA_REALIZADO);
//        }
//
//        boolean pagamentoRealizado = pagamentoClient.gerarQrCode(pedidoDTO.getId());
//
//        if (pagamentoRealizado) {
//            pedidoDTO = statusPedidoPagamentoService.atualizarStatus(pedidoDTO);
//        }
//
//        return new PagamentoDTO(pedidoDTO.getId(), pagamentoRealizado, pedidoDTO.getStatusPedido());
//    }
}
