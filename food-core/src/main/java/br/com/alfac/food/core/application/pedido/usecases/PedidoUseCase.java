package br.com.alfac.food.core.application.pedido.usecases;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.pedido.*;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;

import java.util.List;
import java.util.Optional;

public class PedidoUseCase {

    private final PedidoRepository pedidoRepository;


    public PedidoUseCase(
            final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;

    }

    public List<PedidoDTO> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        return PedidoMapper.mapearParaListaPedidoDTO(pedidos);
    }

    public PedidoDTO consultarPedidoPorId(Long id) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(id);
        return PedidoMapper.mapearParaPedidoDTO(pedidoOpt);
    }





    public PedidoDTO atualizarStatusPedido(Long id) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(id);

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();

        if (StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_PAGO);
        }

        pedido.atualizarStatus();

        Pedido pedidoAtualizado = pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());

        return PedidoMapper.mapearParaPedidoDTO(pedidoAtualizado);
    }

    public List<PedidoDTO> listarPedidosPorStatus(final StatusPedido status)  {

        List<Pedido> pedidos = pedidoRepository.listarPedidosPorStatus(status);

        return PedidoMapper.mapearParaListaPedidoDTO(pedidos);
    }
}
