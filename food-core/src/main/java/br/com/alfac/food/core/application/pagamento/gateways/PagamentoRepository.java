package br.com.alfac.food.core.application.pagamento.gateways;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;

import java.util.Optional;

public interface PagamentoRepository {

    PagamentoEntityDTO criar(PagamentoEntityDTO pagamentoEntityDTO);
    PagamentoEntityDTO atualizar(PagamentoEntityDTO pagamentoEntityDTO);
    Optional<PagamentoEntityDTO> buscarPorId(Long id);


}
