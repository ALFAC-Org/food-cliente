package br.com.alfac.food.infra.pagamento.gateway;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.infra.pagamento.mapper.PagamentoEntityMapper;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntity;
import br.com.alfac.food.infra.pagamento.persistence.PagamentoEntityRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class PagamentoRepositoryImpl implements PagamentoRepository {

    private final PagamentoEntityRepository pagamentoEntityRepository;

    public PagamentoRepositoryImpl(final PagamentoEntityRepository pagamentoEntityRepository) {
        this.pagamentoEntityRepository = pagamentoEntityRepository;
    }

    @Override
    public PagamentoEntityDTO criar(final PagamentoEntityDTO pagamentoEntityDTO) {

        PagamentoEntity pagamentoEntity = PagamentoEntityMapper.toEntity(pagamentoEntityDTO);

        PagamentoEntity pagamentoSalvo = pagamentoEntityRepository.save(pagamentoEntity);

        return PagamentoEntityMapper.toDTO(pagamentoSalvo);
    }

    @Override
    public PagamentoEntityDTO atualizar(final PagamentoEntityDTO pagamentoEntityDTO) {

        pagamentoEntityRepository.findById(pagamentoEntityDTO.id())
                .ifPresent(pagamentoEntity -> {
                    pagamentoEntity.setStatus(pagamentoEntityDTO.status());
                    pagamentoEntity.setDataAtualizacao(LocalDateTime.now());
                    pagamentoEntityRepository.save(pagamentoEntity);
                });

        return pagamentoEntityDTO;
    }

    @Override
    public Optional<PagamentoEntityDTO> buscarPorId(final Long id) {
        return PagamentoEntityMapper.toDTO(pagamentoEntityRepository.findById(id));
    }

    @Override
    public Optional<PagamentoEntityDTO> buscarPorPedidoId(final Long pedidoId) {
        return PagamentoEntityMapper.toDTO(pagamentoEntityRepository.findByPedidoId(pedidoId));
    }
}
