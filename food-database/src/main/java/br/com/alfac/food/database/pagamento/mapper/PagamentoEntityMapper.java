package br.com.alfac.food.database.pagamento.mapper;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.database.pagamento.persistence.PagamentoEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class PagamentoEntityMapper {

    public static PagamentoEntity toEntity(final PagamentoEntityDTO pagamentoEntityDTO) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();

        pagamentoEntity.setId(pagamentoEntityDTO.id());
        pagamentoEntity.setPedidoId(pagamentoEntityDTO.idPedido());
        pagamentoEntity.setDataPagamento(pagamentoEntityDTO.dataPagamento());
        pagamentoEntity.setStatus(pagamentoEntityDTO.status());
        pagamentoEntity.setDataAtualizacao(LocalDateTime.now());

        return pagamentoEntity;
    }

    public static PagamentoEntityDTO toDTO(final PagamentoEntity pagamentoEntity) {
        return new PagamentoEntityDTO(pagamentoEntity.getPedidoId(), pagamentoEntity.getId(), pagamentoEntity.getStatus(), pagamentoEntity.getPedidoId(),
                pagamentoEntity.getDataPagamento());
    }


    public static Optional<PagamentoEntityDTO> toDTO(final Optional<PagamentoEntity> pagamentoEntity) {

        if (pagamentoEntity.isPresent()) {

            return Optional.of(new PagamentoEntityDTO(pagamentoEntity.get().getPedidoId(), pagamentoEntity.get().getId(),
                    pagamentoEntity.get().getStatus(), pagamentoEntity.get().getPedidoId(), pagamentoEntity.get().getDataPagamento()));

        }

        return Optional.empty();
    }
}
