package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.item.gateways.ItemService;
import br.com.alfac.food.core.application.item.usecases.ItemServiceImpl;
import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.application.pagamento.usecases.CriarPagamentoPendenteUserCase;
import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.application.pedido.usecases.PedidoUseCase;

import br.com.alfac.food.infra.pagamento.gateway.RepositorioPagamentoGatewayImpl;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PedidoUseCase pedidoService(RepositorioPedidoGateway pedidoRepository) {
        return new PedidoUseCase(pedidoRepository);
    }

    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemServiceImpl(itemRepository);
    }







}
