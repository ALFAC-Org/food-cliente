package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.exception.FoodException;

import br.com.alfac.food.core.domain.pedido.StatusPedido;

public interface PedidoService {
    
    public List<PedidoDTO> listarPedidos();
    
    public PedidoDTO consultarPedidoPorId(Long id) throws FoodException;
    
    PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws FoodException;

    PedidoDTO atualizarStatusPedido(Long id, StatusPedido statusPedido) throws FoodException;
}
