package br.com.alfac.food.infra.pagamento.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.alfac.food.core.application.pagamento.adapters.gateways.PagamentoClientGateway;

@Service
public class PagamentoClientGatewayImpl implements PagamentoClientGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoClientGatewayImpl.class);

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
