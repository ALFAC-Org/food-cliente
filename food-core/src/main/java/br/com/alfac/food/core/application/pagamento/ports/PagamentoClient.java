package br.com.alfac.food.core.application.pagamento.ports;

public interface PagamentoClient {

    boolean efetuarPagamento(Long idPedido);

}
