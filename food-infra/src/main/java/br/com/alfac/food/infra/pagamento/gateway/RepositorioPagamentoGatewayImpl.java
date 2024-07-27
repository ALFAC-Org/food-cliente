package br.com.alfac.food.infra.pagamento.gateway;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoResponse;
import br.com.alfac.food.core.application.pagamento.adapters.gateways.RepositorioPagamentoGateway;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.infra.pagamento.mapper.PagamentoEntityMapper;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntity;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntityRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class RepositorioPagamentoGatewayImpl implements RepositorioPagamentoGateway {

    private final PagamentoEntityRepository pagamentoEntityRepository;

    public RepositorioPagamentoGatewayImpl(final PagamentoEntityRepository pagamentoEntityRepository) {
        this.pagamentoEntityRepository = pagamentoEntityRepository;
    }

    @Override
    public Pagamento criar(final PagamentoResponse pagamentoResponse) throws FoodException {

        PagamentoEntity pagamentoEntity = PagamentoEntityMapper.toEntity(pagamentoResponse);

        PagamentoEntity pagamentoSalvo = pagamentoEntityRepository.save(pagamentoEntity);

        return PagamentoEntityMapper.toDomain(pagamentoSalvo);
    }

    @Override
    public Pagamento atualizar(final PagamentoResponse pagamentoResponse) throws FoodException {

        Optional<PagamentoEntity> pagamentoEntityOpt = pagamentoEntityRepository.findById(pagamentoResponse.id());


        if (pagamentoEntityOpt
                .isPresent()) {
            PagamentoEntity pagamentoEntity = pagamentoEntityOpt.get();

            pagamentoEntity.setStatus(pagamentoResponse.status());
            pagamentoEntity.setDataAtualizacao(LocalDateTime.now());
            pagamentoEntityRepository.save(pagamentoEntity);
            return PagamentoEntityMapper.toDomain(pagamentoEntity);
        }
        return null;

    }

    @Override
    public Optional<Pagamento> buscarPorId(final Long id) throws FoodException {
        return PagamentoEntityMapper.toDomain(pagamentoEntityRepository.findById(id));
    }

    @Override
    public Optional<Pagamento> buscarPorPedidoId(final Long pedidoId) throws FoodException {
        return PagamentoEntityMapper.toDomain(pagamentoEntityRepository.findByPedidoId(pedidoId));
    }
}
