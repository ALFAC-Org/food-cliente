package br.com.alfac.food.database.pedido.repository;

import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.database.pedido.entity.PedidoEntity;
import br.com.alfac.food.database.pedido.mapper.PedidoEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

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

        List<Pedido> pedidos = new ArrayList<>();

        for (PedidoEntity pedidoEntity : pedidoEntities) {
            Pedido pedido = pedidoEntityMapper.toDomain(pedidoEntity);
            pedidos.add(pedido);
        }

        return pedidos;
    }

    @Override
    public Optional<Pedido> consultarPedidoPorId(Long id) {
        Optional<PedidoEntity> pedidoEntityOpt = pedidoEntityRepository.findById(id);
        return montarPedido(pedidoEntityOpt);
    }

    @Override
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

}
