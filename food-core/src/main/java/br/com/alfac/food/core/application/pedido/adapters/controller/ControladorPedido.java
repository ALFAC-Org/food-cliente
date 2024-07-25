package br.com.alfac.food.core.application.pedido.adapters.controller;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.pagamento.adapters.gateways.PagamentoClientGateway;
import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pagamento.usecases.CriarPagamentoPendenteUserCase;
import br.com.alfac.food.core.application.pagamento.usecases.CriarQrCodePagamento;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.application.pedido.adapters.presenter.PedidoPresenter;
import br.com.alfac.food.core.application.pedido.dto.PedidoCriadoDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.usecases.CriarPedidoUserCase;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.exception.FoodException;

public class ControladorPedido {

    private final CriarPedidoUserCase criarPedidoUserCase;
    private final CriarPagamentoPendenteUserCase criarPagamentoPendenteUserCase;
    private final CriarQrCodePagamento criarQrCodePagamento;

    public ControladorPedido(
            final RepositorioPedidoGateway repositorioPedidoGateway, final RepositorioClienteGateway clienteRepository, final ItemRepository itemRepository,

            final RepositorioPagamentoGateway repositorioPagamentoGateway,
            final PagamentoClientGateway pagamentoClientGateway) {
        this.criarPedidoUserCase = new CriarPedidoUserCase(repositorioPedidoGateway, clienteRepository, itemRepository);
        this.criarPagamentoPendenteUserCase = new CriarPagamentoPendenteUserCase(repositorioPagamentoGateway);
        this.criarQrCodePagamento = new CriarQrCodePagamento(pagamentoClientGateway);
    }

    public PedidoCriadoDTO criarPedido(PedidoDTO pedidoDTO) throws FoodException {

        Pedido pedido = criarPedidoUserCase.executar(pedidoDTO);

        Pagamento pagamento = criarPagamentoPendenteUserCase.executar(pedido.getId());

        String qrCodeParaPagamento = criarQrCodePagamento.executar(pagamento.getId());

        return PedidoPresenter.mapearParaPedidoCriadoDTO(pedido, qrCodeParaPagamento);
    }
}
