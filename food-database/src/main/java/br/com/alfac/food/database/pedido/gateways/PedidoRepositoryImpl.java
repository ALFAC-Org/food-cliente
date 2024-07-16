package br.com.alfac.food.database.pedido.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.database.pedido.mapper.PedidoEntityMapper;
import br.com.alfac.food.database.pedido.persistence.PedidoEntity;
import br.com.alfac.food.database.pedido.persistence.PedidoEntityRepository;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoEntityRepository pedidoEntityRepository;
    private final PedidoEntityMapper pedidoEntityMapper;

    public PedidoRepositoryImpl(final PedidoEntityRepository pedidoEntityRepository,
                                final PedidoEntityMapper pedidoMapper) {
        this.pedidoEntityRepository = pedidoEntityRepository;
        this.pedidoEntityMapper = pedidoMapper;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<PedidoEntity> pedidoEntities = pedidoEntityRepository.findAll();
        return  pedidoEntityMapper.toDomain(pedidoEntities);
    }

    @Override
    public Optional<Pedido> consultarPedidoPorId(Long id) {
        Optional<PedidoEntity> pedidoEntityOpt = pedidoEntityRepository.findById(id);
        return montarPedido(pedidoEntityOpt);
    }

    @Override
    @Transactional
    public Pedido registrarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.toEntity(pedido);

        PedidoEntity pedidoCriado = pedidoEntityRepository.save(pedidoEntity);

        return pedidoEntityMapper.toDomain(pedidoCriado);
    }

    private Optional<Pedido> montarPedido(Optional<PedidoEntity> pedidoEntityOpt) {
        Optional<Pedido> pedidoOpt = Optional.empty();

        if (pedidoEntityOpt.isPresent()) {
            PedidoEntity pedidoEntity = pedidoEntityOpt.get();

            Pedido pedido = pedidoEntityMapper.toDomain(pedidoEntity);

            pedidoOpt = Optional.of(pedido);
        }
        return pedidoOpt;
    }

    @Override
    public Pedido atualizarStatusPedido(Long id, StatusPedido statusPedido) {
        Optional<PedidoEntity> pedidoEntityOpt = pedidoEntityRepository.findById(id);

        if (pedidoEntityOpt.isPresent()) {
            PedidoEntity pedidoEntity = pedidoEntityOpt.get();
            pedidoEntity.setStatus(statusPedido);

            PedidoEntity pedidoAtualizado = pedidoEntityRepository.save(pedidoEntity);

            return pedidoEntityMapper.toDomain(pedidoAtualizado);
        }

        return null;
    }

    @Override
    public List<Pedido> listarPedidosPorStatus(final StatusPedido status) {
        List<PedidoEntity> pedidos = pedidoEntityRepository.findAllByStatus(status);

        return  pedidoEntityMapper.toDomain(pedidos);
    }

}
