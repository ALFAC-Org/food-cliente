package br.com.alfac.food.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.cliente.ports.ClienteService;
import br.com.alfac.food.core.application.cliente.services.ClienteServiceImpl;
import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
import br.com.alfac.food.core.application.pedido.services.PedidoServiceImpl;
import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.application.item.ports.ItemService;
import br.com.alfac.food.core.application.item.services.ItemServiceImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Bean
    public PedidoService pedidoService(PedidoRepository pedidoRepository) {
        return new PedidoServiceImpl(pedidoRepository);
    }

    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemServiceImpl(itemRepository);
    }

}
