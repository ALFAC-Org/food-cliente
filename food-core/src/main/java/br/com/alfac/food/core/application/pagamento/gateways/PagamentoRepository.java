package br.com.alfac.food.core.application.pagamento.gateways;

import java.util.Optional;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;

public interface PagamentoRepository {

    PagamentoEntityDTO criar(PagamentoEntityDTO pagamentoEntityDTO);
    PagamentoEntityDTO atualizar(PagamentoEntityDTO pagamentoEntityDTO);
    Optional<PagamentoEntityDTO> buscarPorId(Long id);
    Optional<PagamentoEntityDTO> buscarPorPedidoId(Long pedidoId);
}
