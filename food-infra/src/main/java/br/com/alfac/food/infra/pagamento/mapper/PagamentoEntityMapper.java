package br.com.alfac.food.infra.pagamento.mapper;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class PagamentoEntityMapper {

    public static PagamentoEntity toEntity(final PagamentoResponse pagamentoResponse) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();

        pagamentoEntity.setId(pagamentoResponse.id());
        pagamentoEntity.setPedidoId(pagamentoResponse.idPedido());
        pagamentoEntity.setDataPagamento(pagamentoResponse.dataPagamento());
        pagamentoEntity.setStatus(pagamentoResponse.status());
        pagamentoEntity.setDataAtualizacao(LocalDateTime.now());

        return pagamentoEntity;
    }

    public static PagamentoResponse toDTO(final PagamentoEntity pagamentoEntity) {
        return new PagamentoResponse(pagamentoEntity.getPedidoId(), pagamentoEntity.getId(), pagamentoEntity.getStatus(), pagamentoEntity.getPedidoId(),
                pagamentoEntity.getDataPagamento());
    }


    public static Optional<PagamentoResponse> toDTO(final Optional<PagamentoEntity> pagamentoEntity) {

        if (pagamentoEntity.isPresent()) {

            return Optional.of(new PagamentoResponse(pagamentoEntity.get().getPedidoId(), pagamentoEntity.get().getId(),
                    pagamentoEntity.get().getStatus(), pagamentoEntity.get().getPedidoId(), pagamentoEntity.get().getDataPagamento()));

        }

        return Optional.empty();
    }

    public static Pagamento toDomain(final PagamentoEntity pagamentoSalvo) throws FoodException {
        return new Pagamento(pagamentoSalvo.getId(), pagamentoSalvo.getStatus(), pagamentoSalvo.getPedidoId(), pagamentoSalvo.getDataPagamento());
    }

    public static Optional<Pagamento> toDomain(final Optional<PagamentoEntity> pagamentoEntityOptional) throws FoodException {

        if (pagamentoEntityOptional.isPresent()) {
            PagamentoEntity pagamentoEntity = pagamentoEntityOptional.get();
            return Optional.of(toDomain(pagamentoEntity));
        }

        return Optional.empty();
    }
}
