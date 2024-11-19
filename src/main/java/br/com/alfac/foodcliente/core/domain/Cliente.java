package br.com.alfac.foodcliente.core.domain;

import br.com.alfac.foodcliente.core.domain.vo.CPF;

import java.util.UUID;

public class Cliente  {
    private CPF cpf;
    private String nome;
    private String email;
    private UUID uuid;
    private Long id;

    public CPF getCpf() {
        return cpf;
    }
    
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    
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


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
}