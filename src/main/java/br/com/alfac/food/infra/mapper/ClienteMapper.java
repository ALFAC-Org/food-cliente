package br.com.alfac.food.infra.mapper;

import br.com.alfac.food.core.application.dto.ClienteDTO;
import br.com.alfac.food.infra.dto.ClienteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    ClienteDTO toDTO(ClienteRequest clienteRequest);

}
