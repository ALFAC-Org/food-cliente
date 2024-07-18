package br.com.alfac.food.core.application.pedido.usecases;

import br.com.alfac.food.core.application.cliente.gateways.ClienteRepository;
import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.gateways.PedidoRepository;
import br.com.alfac.food.core.application.pedido.mappers.PedidoMapper;
import br.com.alfac.food.core.domain.cliente.Cliente;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.domain.pedido.*;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.cliente.ClienteError;
import br.com.alfac.food.core.exception.combo.ComboError;
import br.com.alfac.food.core.exception.item.ItemError;
import br.com.alfac.food.core.utils.CollectionsUtils;

import java.util.Objects;

public class CriarPedido {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemRepository itemRepository;

    public CriarPedido(final PedidoRepository pedidoRepository, final ClienteRepository clienteRepository, final ItemRepository itemRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
    }


    public PedidoDTO executar(PedidoDTO pedidoDTO) throws FoodException {
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
                    .comLanche(buscarLanche(comboDTO.getLanche()))
                    .comAcompanhamento(buscarItem(comboDTO.getAcompanhamento()))
                    .comBebida(buscarItem(comboDTO.getBebida()))
                    .comSobremesa(buscarItem(comboDTO.getSobremesa()))
                    .build();

            pedido.adicionaCombo(combo);
        }

        // Define status inicial do pedido
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        Pedido pedidoSalvo = pedidoRepository.registrarPedido(pedido);

        return PedidoMapper.mapearParaPedidoDTO(pedidoSalvo);
    }

    private Lanche buscarLanche(final LancheDTO lancheDTO) throws FoodException {

        if (Objects.nonNull(lancheDTO)) {
            Long lancheId = lancheDTO.getId();
            Item item = itemRepository.consultarItemPorId(lancheId)
                    .orElseThrow(() -> new FoodException(ItemError.ITEM_NAO_ENCONTRADO, lancheId));

            Lanche lanche = new Lanche();
            lanche.setNome(item.getNome());
            lanche.setId(item.getId());
            lanche.setPreco(item.getPreco());
            lanche.setCategoria(item.getCategoria());
            lanche.setObservacoes(lancheDTO.getObservacoes());
            lanche.setCategoria(item.getCategoria());

            if (CollectionsUtils.naoVazio(lancheDTO.getComplementos())) {
                for (ItemDTO complementoDTO : lancheDTO.getComplementos()) {
                    lanche.adicionaComplemento(buscarItem(complementoDTO));
                }
            }

            return lanche;
        }
        return null;
    }

    private Item buscarItem(final ItemDTO itemDTO) throws FoodException {
        if (Objects.nonNull(itemDTO)) {
            Long itemId = itemDTO.getId();
            return itemRepository.consultarItemPorId(itemId)
                    .orElseThrow(() -> new FoodException(ItemError.ITEM_PEDIDO_INEXISTENTE, itemId));
        }
        return null;
    }
}
