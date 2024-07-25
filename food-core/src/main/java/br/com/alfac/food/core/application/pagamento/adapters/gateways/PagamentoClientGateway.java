package br.com.alfac.food.core.application.pagamento.adapters.gateways;

public interface PagamentoClientGateway {

    String gerarQrCode(Long idPedido);

}
