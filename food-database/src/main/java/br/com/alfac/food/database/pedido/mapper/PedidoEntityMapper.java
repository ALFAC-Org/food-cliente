package br.com.alfac.food.database.pedido.mapper;

import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoEntityMapper {

    PedidoEntity toEntity(Pedido pedido);

    Pedido toDomain(PedidoEntity pedido);

}
