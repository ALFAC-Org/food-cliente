package br.com.alfac.food.core.application.pedido.services;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Pedido;

public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }
    
    public List<Pedido> listarPedidos(){
        return null;
    }
    
    public void registrarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();
        List<Combo> combos = new ArrayList<>();
        
        for(ComboDTO comboDTO : pedidoDTO.getCombos()){
            Combo combo = new Combo();

            if(comboDTO.getLanche() != null){
                //combo.setLanche(findById(comboDTO.getLanche().getItemId()));
            }

            pedido.adicionaCombo(combo);
        }

        pedidoRepository.registrarPedido(pedido);
    }

}
