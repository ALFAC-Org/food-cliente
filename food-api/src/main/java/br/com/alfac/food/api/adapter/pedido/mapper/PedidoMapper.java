package br.com.alfac.food.api.adapter.pedido.mapper;

import br.com.alfac.food.api.adapter.pedido.dto.PedidoRequest;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoDTO toDTO(PedidoRequest pedidoRequest);

}
