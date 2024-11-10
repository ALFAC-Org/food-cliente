package br.com.alfac.food.core.application.cliente.dto;

import java.util.UUID;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
    private Long id;
    private UUID uuid;

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuId(final UUID uuid) {
        this.uuid = uuid;
    }
}