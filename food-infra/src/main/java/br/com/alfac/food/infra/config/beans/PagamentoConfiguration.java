package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.pagamento.adapters.controller.ControladorRecebimentoPagamento;
import br.com.alfac.food.core.application.pagamento.adapters.controller.ControladorPagamento;
import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.infra.pagamento.gateway.RepositorioPagamentoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfiguration {


    @Bean
    public ControladorPagamento controladorPagamento(final RepositorioPagamentoGatewayImpl pagamentoRepository ) {
        return new ControladorPagamento(pagamentoRepository);
    }

    @Bean
    public ControladorRecebimentoPagamento controladoRecebimentoPagamento(final RepositorioPedidoGateway pedidoRepository, final RepositorioPagamentoGateway pagamentoRepository) {
        return new ControladorRecebimentoPagamento(pedidoRepository, pagamentoRepository);
    }
}
