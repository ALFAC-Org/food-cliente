package br.com.alfac.food.infra.pedido.gateways;

import br.com.alfac.food.core.application.pedido.adapters.gateways.RepositorioPedidoGateway;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.infra.pedido.mapper.PedidoEntityMapper;
import br.com.alfac.food.infra.pedido.persistence.PedidoEntity;
import br.com.alfac.food.infra.pedido.persistence.PedidoEntityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RepositorioPedidoGatewayImpl implements RepositorioPedidoGateway {

    private final PedidoEntityRepository pedidoEntityRepository;

    public RepositorioPedidoGatewayImpl(final PedidoEntityRepository pedidoEntityRepository) {
        this.pedidoEntityRepository = pedidoEntityRepository;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<PedidoEntity> pedidoEntities = pedidoEntityRepository.findAll();
        return PedidoEntityMapper.INSTANCE.toDomain(pedidoEntities);
    }

    @Override
    public Optional<Pedido> consultarPedidoPorId(Long id) throws FoodException {
        Optional<PedidoEntity> pedidoEntityOpt = pedidoEntityRepository.findById(id);
        return montarPedido(pedidoEntityOpt);
    }

    @Override
    @Transactional
    public Pedido registrarPedido(Pedido pedido) throws FoodException {
        PedidoEntity pedidoEntity = PedidoEntityMapper.INSTANCE.toEntity(pedido);

        PedidoEntity pedidoCriado = pedidoEntityRepository.save(pedidoEntity);

        return PedidoEntityMapper.INSTANCE.toDomain(pedidoCriado);
    }

    private Optional<Pedido> montarPedido(Optional<PedidoEntity> pedidoEntityOpt) throws FoodException {
        Optional<Pedido> pedidoOpt = Optional.empty();

        if (pedidoEntityOpt.isPresent()) {
            PedidoEntity pedidoEntity = pedidoEntityOpt.get();

            Pedido pedido = PedidoEntityMapper.INSTANCE.toDomain(pedidoEntity);

            pedidoOpt = Optional.of(pedido);
        }
        return pedidoOpt;
    }

    @Override
    public Pedido atualizarStatusPedido(Long id, StatusPedido statusPedido) throws FoodException {
        Optional<PedidoEntity> pedidoEntityOpt = pedidoEntityRepository.findById(id);

        if (pedidoEntityOpt.isPresent()) {
            PedidoEntity pedidoEntity = pedidoEntityOpt.get();
            pedidoEntity.setStatus(statusPedido);

            PedidoEntity pedidoAtualizado = pedidoEntityRepository.save(pedidoEntity);

            return PedidoEntityMapper.INSTANCE.toDomain(pedidoAtualizado);
        }

        return null;
    }

    @Override
    public List<Pedido> listarPedidosPorStatus(final StatusPedido status) {
        List<PedidoEntity> pedidos = pedidoEntityRepository.findAllByStatus(status);

        return PedidoEntityMapper.INSTANCE.toDomain(pedidos);
    }

}
