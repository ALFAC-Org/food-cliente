package br.com.alfac.food.core.application.pedido.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.gateways.StatusPedidoPagamentoService;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

public class StatusPedidoPagamentoServiceImpl implements StatusPedidoPagamentoService {

    private final PedidoRepository pedidoRepository;

    public StatusPedidoPagamentoServiceImpl(final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;

    }

    @Override
    public PedidoDTO atualizarStatus(final PedidoDTO pedidoDTO) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(pedidoDTO.getId());

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();

        if (!StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())) {
            throw new FoodException(PagamentoErro.PAGAMENTO_JA_REALIZADO);
        }

        pedido.atualizarStatus();

        Pedido pedidoAtualizado = pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());

        return PedidoMapper.mapearParaPedidoDTO(pedidoAtualizado);
    }
}
