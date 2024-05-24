package br.com.alfac.food.core.application.pedido.services;

import br.com.alfac.food.core.application.cliente.ports.ClienteRepository;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.application.pedido.ports.PedidoRepository;
import br.com.alfac.food.core.application.pedido.ports.PedidoService;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.*;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;
import br.com.alfac.food.core.exception.combo.ComboError;
import br.com.alfac.food.core.exception.item.ItemError;
import br.com.alfac.food.core.exception.pedido.PedidoErros;
import br.com.alfac.food.core.utils.CollectionsUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemRepository itemRepository;

    public PedidoServiceImpl(
            final PedidoRepository pedidoRepository,
            final ClienteRepository clienteRepository,
            final ItemRepository itemRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
    }

    public List<PedidoDTO> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        return PedidoMapper.mapearParaListaPedidoDTO(pedidos);
    }

    public PedidoDTO consultarPedidoPorId(Long id) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(id);
        return PedidoMapper.mapearParaPedidoDTO(pedidoOpt);
    }

    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws FoodException {
        Pedido pedido = new Pedido();

        if (pedidoDTO.getClienteId() != null) {
            Cliente cliente = clienteRepository.consultarClientePorId(pedidoDTO.getClienteId()).orElseThrow(() -> new FoodException(ClienteError.CLIENTE_NAO_EXISTENTE));
            pedido.setCliente(cliente);
        }
        if (CollectionsUtils.vazio(pedidoDTO.getCombos())) {
            throw new FoodException(ComboError.COMBO_VAZIO);
        }

        for (ComboDTO comboDTO : pedidoDTO.getCombos()) {
            Combo combo = ComboBuilder.combo()
                    .comLanche(getLanche(comboDTO.getLanche()))
                    .comAcompanhamento(getItem(comboDTO.getAcompanhamento()))
                    .comBebida(getItem(comboDTO.getBebida()))
                    .comSobremesa(getItem(comboDTO.getSobremesa()))
                    .build();

            pedido.adicionaCombo(combo);
        }

        // Define status inicial do pedido
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        Pedido pedidoSalvo = pedidoRepository.registrarPedido(pedido);

        return consultarPedidoPorId(pedidoSalvo.getId());
    }

    private Item getItem(final ItemDTO itemDTO) throws FoodException {
        if (Objects.nonNull(itemDTO)) {
            Long itemId = itemDTO.getId();
            return itemRepository.consultarItemPorId(itemId)
                    .orElseThrow(() -> new FoodException(ItemError.ITEM_PEDIDO_INEXISTENTE, itemId));
        }
        return null;
    }

    private Lanche getLanche(final LancheDTO lancheDTO) throws FoodException {

        if (Objects.nonNull(lancheDTO)) {
            Long lancheId = lancheDTO.getId();
            Item item = itemRepository.consultarItemPorId(lancheId)
                    .orElseThrow(() -> new FoodException(ItemError.ITEM_NAO_ENCONTRADO, lancheId));

            Lanche lanche = new Lanche();
            lanche.setId(item.getId());
            lanche.setPreco(item.getPreco());
            lanche.setCategoria(item.getCategoria());
            lanche.setObservacoes(lancheDTO.getObservacoes());
            lanche.setCategoria(item.getCategoria());

            if (CollectionsUtils.naoVazio(lancheDTO.getComplementos())) {
                for (ItemDTO complementoDTO : lancheDTO.getComplementos()) {
                    lanche.adicionaComplemento(getItem(complementoDTO));
                }
            }

            return lanche;
        }
        return null;
    }

    public PedidoDTO atualizarStatusPedido(Long id) throws FoodException {
        Optional<Pedido> pedidoOpt = pedidoRepository.consultarPedidoPorId(id);

        if (pedidoOpt.isEmpty()) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_ENCONTRADO);
        }

        Pedido pedido = pedidoOpt.get();

        if (StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())) {
            throw new FoodException(PedidoErros.PEDIDO_NAO_PAGO);
        }

        pedido.atualizarStatus();

        Pedido pedidoAtualizado = pedidoRepository.atualizarStatusPedido(pedido.getId(), pedido.getStatus());

        return PedidoMapper.mapearParaPedidoDTO(pedidoAtualizado);
    }
}
