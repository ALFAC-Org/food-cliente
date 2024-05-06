package br.com.alfac.food.api.dto;


import br.com.alfac.food.core.application.cliente.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteRequest {

    @Schema(example = "John Doe")
    private String nome;

    private String email;

    private String cpf;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ClienteDTO toDTO() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(this.getNome());
        clienteDTO.setEmail(this.getEmail());
        clienteDTO.setCpf(this.getCpf());

        return clienteDTO;
    }
}