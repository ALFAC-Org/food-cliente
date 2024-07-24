package br.com.alfac.food.infra.cliente.mapper;

import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.infra.cliente.persistence.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    @Mapping(target = "cpf", source = "cpf.numero")
    ClienteEntity toEntity(Cliente cliente);

    @Mapping(target = "cpf.numero", source = "cpf")
    Cliente toDomain(ClienteEntity cliente);
}
