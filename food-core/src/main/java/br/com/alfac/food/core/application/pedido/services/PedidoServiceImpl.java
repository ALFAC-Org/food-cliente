package br.com.alfac.food.core.application.pedido.services;

import java.util.List;

import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
import br.com.alfac.food.core.domain.pedido.Pedido;

public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }
    
    public List<Pedido> listarPedidos(){
        return null;
    }
    
    public void enviarPedido(Pedido pedido){
        
    }

}
