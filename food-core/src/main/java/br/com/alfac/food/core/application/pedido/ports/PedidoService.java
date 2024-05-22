package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.exception.FoodException;

public interface PedidoService {
    
    public List<PedidoDTO> listarPedidos();
    
    PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws FoodException;
}
