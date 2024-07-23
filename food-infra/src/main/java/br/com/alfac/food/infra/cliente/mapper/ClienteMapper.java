package br.com.alfac.food.infra.cliente.mapper;

import br.com.alfac.food.infra.cliente.dto.ClienteRequest;
import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(ClienteRequest clienteRequest);

}
