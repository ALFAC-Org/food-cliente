package br.com.alfac.food.database.cliente.mapper;

import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.database.cliente.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    @Mapping(target = "cpf", source = "cpf.numero")
    // @Mapping(target = "id", source = "id", ignore = true)
    // @Mapping(target = "uuid", source = "id")
    // @Mapping(target = "id", ignore = true)
    ClienteEntity toEntity(Cliente cliente);

    @Mapping(target = "cpf.numero", source = "cpf")
    // @Mapping(target = "id", source = "uuid")
    Cliente toDomain(ClienteEntity cliente);
}
