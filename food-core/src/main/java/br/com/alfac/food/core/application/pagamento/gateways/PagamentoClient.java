package br.com.alfac.food.core.application.pagamento.gateways;

public interface PagamentoClient {

    boolean efetuarPagamento(Long idPedido);

}
