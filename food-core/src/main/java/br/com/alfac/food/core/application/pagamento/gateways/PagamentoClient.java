package br.com.alfac.food.core.application.pagamento.gateways;

public interface PagamentoClient {

    String gerarQrCode(Long idPedido);

}
