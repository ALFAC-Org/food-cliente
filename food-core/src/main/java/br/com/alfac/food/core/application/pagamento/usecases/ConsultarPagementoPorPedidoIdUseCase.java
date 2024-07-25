package br.com.alfac.food.core.application.pagamento.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;

public class ConsultarPagementoPorPedidoIdUseCase {
    private final RepositorioPagamentoGateway pagamentoRepository;

    public ConsultarPagementoPorPedidoIdUseCase(RepositorioPagamentoGateway pagamentoRepository){
        this.pagamentoRepository = pagamentoRepository;
    }
    
    public Pagamento executar(final Long pedidoId) throws FoodException {
        
        Optional<Pagamento> pagamento = pagamentoRepository.buscarPorPedidoId(pedidoId);
        
        if (pagamento.isEmpty()) {
            throw new FoodException(PagamentoErro.PAGAMENTO_NAO_ENCONTRADO);
        }

        return pagamento.get();
    }
}
