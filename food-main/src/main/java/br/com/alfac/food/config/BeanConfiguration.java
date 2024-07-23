package br.com.alfac.food.config;

import br.com.alfac.food.core.application.cliente.gateways.ClienteRepository;
import br.com.alfac.food.core.application.cliente.gateways.ClienteService;
import br.com.alfac.food.core.application.cliente.usecases.ClienteServiceImpl;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.item.gateways.ItemService;
import br.com.alfac.food.core.application.item.usecases.ItemServiceImpl;
import br.com.alfac.food.core.application.pagamento.controller.ControladoRecebimentoPagamento;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.core.application.pagamento.usecases.AlterarStatusPagamentoRealizado;
import br.com.alfac.food.core.application.pagamento.usecases.CriarPagamentoPendente;
import br.com.alfac.food.core.application.pedido.controller.ControladorPedido;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.gateways.StatusPedidoPagamentoService;
import br.com.alfac.food.core.application.pedido.usecases.CriarPedido;
import br.com.alfac.food.core.application.pedido.usecases.PedidoUseCase;
import br.com.alfac.food.core.application.pedido.usecases.AtualizarStatusPedidoPagamentoRecebido;
import br.com.alfac.food.database.pagamento.gateway.PagamentoRepositoryImpl;
import br.com.alfac.food.database.pagamento.persistence.PagamentoEntityRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Bean
    public PedidoUseCase pedidoService(PedidoRepository pedidoRepository) {
        return new PedidoUseCase(pedidoRepository);
    }

    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemServiceImpl(itemRepository);
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
    public CriarPedido criarPedido(final PedidoRepository pedidoRepository, final ClienteRepository clienteRepository, final ItemRepository itemRepository) {
        return new CriarPedido(pedidoRepository, clienteRepository, itemRepository);
    }

    @Bean
    public ControladorPedido controladorPedido(final CriarPedido criarPedido, final CriarPagamentoPendente criarPagamentoPendente, final PagamentoClient pagamentoClient, final ConsultarPagementoPorPedidoIdUseCase consultarPagementoPorPedidoIdUseCase) {
        return new ControladorPedido(criarPedido, criarPagamentoPendente, pagamentoClient, consultarPagementoPorPedidoIdUseCase);
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
