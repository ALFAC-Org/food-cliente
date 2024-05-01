package br.com.alfac.food.core.domain.cliente;

import br.com.alfac.food.core.domain.base.AggregateRoot;
import br.com.alfac.food.core.domain.base.AssertionConcern;
import br.com.alfac.food.core.domain.cliente.vo.CPF;

public class Cliente implements AggregateRoot {
    private CPF cpf;
    private String nome;

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

    public void validateEntity(){
        AssertionConcern.assertArgumentNotEmpty(nome, "O nome do cliente não pode estar vazio.");
    }
    
}