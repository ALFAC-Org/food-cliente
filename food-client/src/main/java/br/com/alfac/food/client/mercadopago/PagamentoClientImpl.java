package br.com.alfac.food.client.mercadopago;

import br.com.alfac.food.core.application.pagamento.ports.PagamentoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PagamentoClientImpl implements PagamentoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoClientImpl.class);

    @Override
    public boolean efetuarPagamento(final Long idPedido) {
        LOGGER.debug("Efetuando pagamento idPedido {}", idPedido);
        return true;
    }
}
