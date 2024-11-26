package br.com.alfac.foodcliente.utils;

import br.com.alfac.foodcliente.core.application.dto.ClienteDTO;
import br.com.alfac.foodcliente.core.domain.Cliente;
import br.com.alfac.foodcliente.core.domain.vo.CPF;
import br.com.alfac.foodcliente.infra.dto.ClienteRequest;

import java.util.Random;
import java.util.UUID;

public abstract class ClienteHelper {
    
    public static ClienteRequest criarClienteRequest() {
        ClienteRequest clienteRequest = new ClienteRequest();

        String cpf = gerarCpfRandom();
        clienteRequest.setCpf(cpf);
        clienteRequest.setNome("Ayrton Senna");
        clienteRequest.setEmail(cpf + "@teste.com.br");

        return clienteRequest;
    }

    public static ClienteDTO criarClienteDTO() {
        return criarClienteDTO(null, null, null);
    }

    public static ClienteDTO criarClienteDTO(String cpf, String nome, String email) {
        ClienteDTO clienteDto = new ClienteDTO();

        clienteDto.setCpf(new CPF(cpf != null && !cpf.isEmpty() ? cpf : "12345678901").getNumero());
        clienteDto.setNome(nome != null && !nome.isEmpty() ? nome : "John Doe");
        clienteDto.setEmail(email != null && !email.isEmpty() ? email : "john@doe.com");

        return clienteDto;
    }

    public static Cliente criarCliente() {
        return criarCliente(null, null, null);
    }

    public static Cliente criarCliente(String cpf, String nome, String email) {
        Cliente cliente = new Cliente();
        long id = 123;

        cliente.setId(id);
        cliente.setUuid(UUID.randomUUID());
        cliente.setCpf(new CPF(cpf != null && !cpf.isEmpty() ? cpf : "12345678901"));
        cliente.setNome(nome != null && !nome.isEmpty() ? nome : "John Doe");
        cliente.setEmail(email != null && !email.isEmpty() ? email : "john@doe.com");

        return cliente;
    }

    public static String gerarCpfRandom() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(11);

        for (int i = 0; i < 11; i++) {
            int numero = random.nextInt(10); // Gera um número de 0 a 9
            stringBuilder.append(numero);
        }

        return stringBuilder.toString();
    }

}
