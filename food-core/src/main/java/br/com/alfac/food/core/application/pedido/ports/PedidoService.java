package br.com.alfac.food.core.application.pedido.ports;

import java.util.List;

import br.com.alfac.food.core.domain.pedido.Pedido;

public interface PedidoService {
    
    public List<Pedido> listarPedidos();
    
    public void enviarPedido(Pedido pedido);
    
}
