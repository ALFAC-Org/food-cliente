package br.com.alfac.foodcliente.infra.mapper;

import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.infra.dto.ClienteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    ClienteDTO toDTO(ClienteRequest clienteRequest);

}
