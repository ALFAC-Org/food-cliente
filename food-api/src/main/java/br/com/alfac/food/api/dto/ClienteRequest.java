package br.com.alfac.food.api.dto;


import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteRequest {

    @Schema(example = "John Doe")
    private String nome;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClienteDTO toDTO() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(this.getNome());
        return clienteDTO;
    }
}