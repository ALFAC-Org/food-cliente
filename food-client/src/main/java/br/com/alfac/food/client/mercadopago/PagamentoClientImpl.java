package br.com.alfac.food.client.mercadopago;

import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pedido.PedidoErros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;

@Service
public class PagamentoClientImpl implements PagamentoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoClientImpl.class);

    /**
     * Simula uma chamada para o Mercado Pago retornando um id supondo que seria um QRCode
     * @param idPedido
     * @return
     */
    @Override
    public String gerarQrCode(final Long idPedido) {
        LOGGER.debug("Efetuando pagamento idPedido {}", idPedido);
        return idPedido.toString();
    }
}
