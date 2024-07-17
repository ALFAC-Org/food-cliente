package br.com.alfac.food.config;

import br.com.alfac.food.core.application.pagamento.gateways.PagamentoClient;
import br.com.alfac.food.core.application.pagamento.usecases.RegistrarPagamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alfac.food.core.application.cliente.gateways.ClienteRepository;
import br.com.alfac.food.core.application.cliente.gateways.ClienteService;
import br.com.alfac.food.core.application.cliente.usecases.ClienteServiceImpl;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.item.gateways.ItemService;
import br.com.alfac.food.core.application.item.usecases.ItemServiceImpl;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.gateways.PedidoService;
import br.com.alfac.food.core.application.pedido.gateways.StatusPedidoPagamentoService;
import br.com.alfac.food.core.application.pedido.usecases.PedidoServiceImpl;
import br.com.alfac.food.core.application.pedido.usecases.StatusPedidoPagamentoServiceImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }
    @Bean
    public PedidoService pedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ItemRepository itemRepository) {
        return new PedidoServiceImpl(pedidoRepository, clienteRepository, itemRepository);
    }
    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemServiceImpl(itemRepository);
    }

    @Bean
    public RegistrarPagamento pagamentoService(PagamentoClient pagamentoClient, PedidoService pedidoService, StatusPedidoPagamentoService statusPedidoPagamentoService) {
        return new RegistrarPagamento(pagamentoClient, pedidoService, statusPedidoPagamentoService);
    }

    @Bean
    public StatusPedidoPagamentoService statusPedidoPagamentoService(final PedidoRepository pedidoRepository) {
        return new StatusPedidoPagamentoServiceImpl(pedidoRepository);
    }
}
