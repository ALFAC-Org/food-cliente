package br.com.alfac.food.api.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClienteRequest {

    @Schema(example = "John Doe")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;

    @Email
    @Schema(example = "teste@teste.com")
    @NotEmpty(message = "Email é obrigatório.")
    private String email;

    @Schema(example = "12345678910")
    @NotEmpty(message = "CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "Tamanho mínimo e máximo do CPF são 11 caracteres")
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

}