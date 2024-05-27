package br.com.alfac.food.core.application.pedido.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteErros;

public final class PedidoMapper {

    private PedidoMapper() {
    }

    public static List<PedidoDTO> mapearParaListaPedidoDTO(List<Pedido> pedidos) {
        List<PedidoDTO> listaPedidoDTO = new ArrayList<>();
        for(Pedido pedido : pedidos){
            listaPedidoDTO.add(mapearParaPedidoDTO(pedido));
        }
        return listaPedidoDTO;
    }

    public static PedidoDTO mapearParaPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setClienteId(pedido.getCliente().getId());

        List<ComboDTO> combosDTO = new ArrayList<>();
        for(Combo combo : pedido.getCombos()){
            ComboDTO comboDTO = new ComboDTO();
            comboDTO.setLanche((LancheDTO) ItemPedidoMapper.mapearParaItemDTO(combo.getLanche()));
            comboDTO.setAcompanhamento(ItemPedidoMapper.mapearParaItemDTO(combo.getAcompanhamento()));
            comboDTO.setBebida(ItemPedidoMapper.mapearParaItemDTO(combo.getBebida()));
            comboDTO.setSobremesa(ItemPedidoMapper.mapearParaItemDTO(combo.getSobremesa()));
            
            combosDTO.add(comboDTO);
        }

        pedidoDTO.setCombos(combosDTO);

        return pedidoDTO;
    }

    public static PedidoDTO mapearParaPedidoDTO(Optional<Pedido> pedidoOpt) throws FoodException {

        Pedido pedido = pedidoOpt.orElseThrow(() -> new FoodException(ClienteErros.CLIENTE_NAO_ENCONTRADO));

        return mapearParaPedidoDTO(pedido);
    }

}