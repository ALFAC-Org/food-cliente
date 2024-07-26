package br.com.alfac.food.core.application.pedido.usecases;

import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

import java.util.Optional;

public class AtualizarStatusPedidoUseCase {

    private final RepositorioPedidoGateway pedidoRepository;

    public AtualizarStatusPedidoUseCase(final RepositorioPedidoGateway pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    public Pedido executar(Long id) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(id);

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();

        if (StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_PAGO);
        }

        pedido.atualizarStatus();

        return pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());
    }

}
