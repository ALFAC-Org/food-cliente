package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.cliente.adapters.gateways.RepositorioClienteGateway;
import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.food.core.application.pagamento.controller.ControladoRecebimentoPagamento;
import br.com.alfac.food.core.application.pagamento.controller.ControladorPagamento;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.core.application.pagamento.usecases.AlterarStatusPagamentoRealizado;
import br.com.alfac.food.core.application.pagamento.usecases.CriarPagamentoPendente;
import br.com.alfac.food.core.application.pedido.controller.ControladorPedido;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.usecases.CriarPedido;
import br.com.alfac.food.core.application.pedido.usecases.PedidoUseCase;
import br.com.alfac.food.core.application.pedido.usecases.AtualizarStatusPedidoPagamentoRecebido;

import br.com.alfac.food.infra.pagamento.gateway.PagamentoRepositoryImpl;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntityRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;

@Configuration
public class BeanConfiguration {

    @Bean
    public PedidoUseCase pedidoService(PedidoRepository pedidoRepository) {
        return new PedidoUseCase(pedidoRepository);
    }

    @Bean
    public PagamentoRepository pagamentoRepository(final PagamentoEntityRepository pagamentoEntityRepository) {
        return new PagamentoRepositoryImpl(pagamentoEntityRepository);
    }

    @Bean
    public CriarPagamentoPendente pagamentoService(PagamentoRepository pagamentoRepository) {
        return new CriarPagamentoPendente(pagamentoRepository);
    }

    @Bean
    public ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase(final PagamentoRepository pagamentoRepository) {
        return new ConsultarPagementoPorPedidoIdUseCase(pagamentoRepository);
    }

    @Bean
    public AtualizarStatusPedidoPagamentoRecebido statusPedidoPagamentoService(final PedidoRepository pedidoRepository) {
        return new AtualizarStatusPedidoPagamentoRecebido(pedidoRepository);
    }

    @Bean
    public CriarPedido criarPedido(final PedidoRepository pedidoRepository, final RepositorioClienteGateway repositorioClienteGatewayMySQL, final RepositorioItemGateway itemRepository) {
        return new CriarPedido(pedidoRepository, repositorioClienteGatewayMySQL, itemRepository);
    }

    @Bean
    public ControladorPedido controladorPedido(final CriarPedido criarPedido, final CriarPagamentoPendente criarPagamentoPendente, final PagamentoClient pagamentoClient) {
        return new ControladorPedido(criarPedido, criarPagamentoPendente, pagamentoClient);
    }

    @Bean
    public ControladorPagamento controladorPagamento(final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase) {
        return new ControladorPagamento(consultarPagementoPorPedidoIdUseCase);
    }


    @Bean
    public AlterarStatusPagamentoRealizado alterarStatusPagamentoRealizado(final PagamentoRepository pagamentoRepository) {
        return new AlterarStatusPagamentoRealizado(pagamentoRepository);
    }

    @Bean
    public ControladoRecebimentoPagamento controladoRecebimentoPagamento(final AlterarStatusPagamentoRealizado alterarStatusPagamentoRealizado,
                                                                         final AtualizarStatusPedidoPagamentoRecebido atualizarStatusPedidoPagamentoRecebido) {
        return new ControladoRecebimentoPagamento(alterarStatusPagamentoRealizado, atualizarStatusPedidoPagamentoRecebido);
    }
}
