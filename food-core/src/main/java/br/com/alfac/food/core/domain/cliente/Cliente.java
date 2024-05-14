package br.com.alfac.food.core.domain.cliente;

import br.com.alfac.food.core.domain.base.AggregateRoot;
import br.com.alfac.food.core.domain.base.AssertionConcern;
import br.com.alfac.food.core.domain.cliente.vo.CPF;

import java.util.UUID;

public class Cliente implements AggregateRoot {
    private CPF cpf;
    private String nome;
    private String email;
    private UUID id;

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

    public void validateEntity() {
        AssertionConcern.assertArgumentNotEmpty(nome, "O nome do cliente não pode estar vazio.");
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
}