package br.com.alfac.food.core.application.pagamento.usecases;

import java.util.Optional;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;

public class ConsultarPagementoPorPedidoIdUseCase {
    private final PagamentoRepository pagamentoRepository;

    public ConsultarPagementoPorPedidoIdUseCase(PagamentoRepository pagamentoRepository){
        this.pagamentoRepository = pagamentoRepository;
    }
    
    public PagamentoEntityDTO executar(final Long idPedido) throws FoodException {
        
        Optional<PagamentoEntityDTO> pagamento = pagamentoRepository.buscarPorPedidoId(idPedido);
        
        if (pagamento.isEmpty()) {
            throw new FoodException(PagamentoErro.PAGAMENTO_NAO_ENCONTRADO);
        }

        return pagamento.get();
    }
}
