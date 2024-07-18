package br.com.alfac.food.core.application.pedido.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

public class AtualizarStatusPedidoPagamentoRecebido {

    private final PedidoRepository pedidoRepository;

    public AtualizarStatusPedidoPagamentoRecebido(final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;

    }

    public PedidoDTO executar(final Long idPedido) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(idPedido);

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();
        pedido.atualizarStatusRecebido();

        Pedido pedidoAtualizado = pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());

        return PedidoMapper.mapearParaPedidoDTO(pedidoAtualizado);
    }
}
