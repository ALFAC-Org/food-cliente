package br.com.alfac.food.config;

import br.com.alfac.food.core.application.cliente.gateways.ClienteRepositoryInterface;
import br.com.alfac.food.core.application.cliente.usecases.cadastrarcliente.CadastrarClienteUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporcpf.ConsultarClientePorCpfInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporcpf.ConsultarClientePorCpfUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporid.ConsultarClientePorIdInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporid.ConsultarClientePorIdUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporuuidu.ConsultarClientePorUuidUseCase;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.item.gateways.ItemService;
import br.com.alfac.food.core.application.item.usecases.ItemServiceImpl;
import br.com.alfac.food.core.application.pagamento.controller.ControladoRecebimentoPagamento;
import br.com.alfac.food.core.application.pagamento.controller.ControladorPagamento;
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

import br.com.alfac.food.core.application.cliente.controller.ControladorCliente;
import br.com.alfac.food.core.application.cliente.usecases.cadastrarcliente.CadastrarClienteInterfaceUseCase;
import br.com.alfac.food.core.application.cliente.usecases.consultarclienteporuuidu.ConsultarClientePorUuidInterfaceUseCase;
import br.com.alfac.food.core.application.pagamento.usecases.ConsultarPagementoPorPedidoIdUseCase;

@Configuration
public class BeanConfiguration {

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
    public CriarPedido criarPedido(final PedidoRepository pedidoRepository, final ClienteRepositoryInterface clienteRepository, final ItemRepository itemRepository) {
        return new CriarPedido(pedidoRepository, clienteRepository, itemRepository);
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
    public ConsultarClientePorCpfUseCase consultarClientePorCpfUseCase(final ClienteRepositoryInterface clienteRepository){
        return new ConsultarClientePorCpfUseCase(clienteRepository);
    }

    @Bean
    public ConsultarClientePorIdUseCase consultarClientePorId(final ClienteRepositoryInterface clienteRepository){
        return new ConsultarClientePorIdUseCase(clienteRepository);
    }

    @Bean
    public ConsultarClientePorUuidUseCase consultarClientePorUuid(final ClienteRepositoryInterface clienteRepository){
        return new ConsultarClientePorUuidUseCase(clienteRepository);
    }

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(final ClienteRepositoryInterface clienteRepository){
        return new CadastrarClienteUseCase(clienteRepository);
    }

    @Bean
    public ControladorCliente controladorCliente(final ConsultarClientePorCpfInterfaceUseCase consultarClientePorCpfUseCase,
    final ConsultarClientePorIdInterfaceUseCase consultarClientePorId,
    final ConsultarClientePorUuidInterfaceUseCase consultarClientePorUuid,
    final CadastrarClienteInterfaceUseCase cadastrarClienteUseCase) {
        return new ControladorCliente(consultarClientePorCpfUseCase, consultarClientePorId, consultarClientePorUuid, cadastrarClienteUseCase);
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
