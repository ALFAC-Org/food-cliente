package br.com.alfac.food.core.application.pedido.adapters.gateways;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;

import java.util.List;

public interface PedidoService {
    
    public List<PedidoDTO> listarPedidos();
    
    public PedidoDTO consultarPedidoPorId(Long id) throws FoodException;
    
    PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws FoodException;

    PedidoDTO atualizarStatusPedido(Long id) throws FoodException;

    List<PedidoDTO> listarPedidosPorStatus(StatusPedido status);
}
