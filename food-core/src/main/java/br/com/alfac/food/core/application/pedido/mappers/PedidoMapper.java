package br.com.alfac.food.core.application.pedido.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
// import br.com.alfac.food.core.domain.cliente.Cliente;
// import br.com.alfac.food.core.domain.item.Item;
// import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteErros;

public final class PedidoMapper {

    private PedidoMapper() {
    }

    public static PedidoDTO mapearParaPedidoDTO(Pedido pedido) {
        List<ComboDTO> comboDTOs = new ArrayList<>();
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setClienteId(pedido.getId());

        // TODO: Implementar mapeamento de combos
        // comboDTOs.forEach(comboDTO -> {
        //     Combo combo = new Combo();
        //     comboDTO.setLanche(null);
        //     comboDTOs.add(comboDTO);
        // });

        // pedidoDTO.setClienteUuId(pedido.getClienteUuId());
        // pedidoDTO.setCombos(pedido.getCombos());
        pedidoDTO.setCombos(comboDTOs);

        return pedidoDTO;
    }

    public static PedidoDTO mapearParaPedidoDTO(Optional<Pedido> pedidoOpt) throws FoodException {

        Pedido pedido = pedidoOpt.orElseThrow(() -> new FoodException(ClienteErros.CLIENTE_NAO_ENCONTRADO));

        return mapearParaPedidoDTO(pedido);
    }
}