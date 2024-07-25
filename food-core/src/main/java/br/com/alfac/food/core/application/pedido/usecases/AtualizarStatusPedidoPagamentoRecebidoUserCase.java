package br.com.alfac.food.core.application.pedido.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.application.pedido.adapters.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

public class AtualizarStatusPedidoPagamentoRecebidoUserCase {

    private final RepositorioPedidoGateway pedidoRepository;

    public AtualizarStatusPedidoPagamentoRecebidoUserCase(final RepositorioPedidoGateway pedidoRepository) {
        this.pedidoRepository = pedidoRepository;

    }

    public Pedido executar(final Long idPedido) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(idPedido);

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();
        pedido.atualizarStatusRecebido();

        return pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());
    }
}
