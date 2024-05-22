package br.com.alfac.food.core.application.pedido.services;

import java.util.List;
import java.util.Optional;

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
import br.com.alfac.food.core.domain.pedido.Combo;
import br.com.alfac.food.core.domain.pedido.Lanche;
import br.com.alfac.food.core.domain.pedido.Pedido;
import br.com.alfac.food.core.domain.pedido.StatusPedido;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.item.ItemErros;
import br.com.alfac.food.core.utils.CollectionsUtils;

public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ItemRepository itemRepository;

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

    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) throws FoodException {
        Pedido pedido = new Pedido();

        if (pedidoDTO.getClienteId() != null) {
            Optional<Cliente> cliente = clienteRepository.consultarClientePorId(pedidoDTO.getClienteId());
            pedido.setCliente(cliente.get());
        }

        for (ComboDTO comboDTO : pedidoDTO.getCombos()) {
            Combo combo = new Combo();

            if (comboDTO.getLanche() != null) {
                LancheDTO lancheDTO = comboDTO.getLanche();

                Item item = itemRepository.consultarItemPorId(lancheDTO.getId())
                        .orElseThrow(() -> new FoodException(ItemErros.ITEM_NAO_ENCONTRADO));

                Lanche lanche = new Lanche();
                lanche.setId(item.getId());
                lanche.setPreco(item.getPreco());
                lanche.setObservacoes(lancheDTO.getObservacoes());

                if (CollectionsUtils.naoVazio(lancheDTO.getComplementos())) {
                    for (ItemDTO complementoDTO : lancheDTO.getComplementos()) {
                        Optional<Item> complemento = itemRepository.consultarItemPorId(complementoDTO.getId());
                        lanche.adicionaComplemento(complemento.get());
                    }
                }

                combo.setLanche(lanche);
            }
            if (comboDTO.getAcompanhamento() != null) {
                Optional<Item> item = itemRepository.consultarItemPorId(comboDTO.getAcompanhamento().getId());
                combo.setAcompanhamento(item.get());
            }
            if (comboDTO.getBebida() != null) {
                Optional<Item> item = itemRepository.consultarItemPorId(comboDTO.getBebida().getId());
                combo.setBebida(item.get());
            }
            if (comboDTO.getSobremesa() != null) {
                Optional<Item> item = itemRepository.consultarItemPorId(comboDTO.getSobremesa().getId());
                combo.setSobremesa(item.get());
            }

            combo.validarItens();

            pedido.adicionaCombo(combo);
        }

        // Define status inicial do pedido
        pedido.setStatus(StatusPedido.RECEBIDO);

        Pedido pedidoSalvo = pedidoRepository.registrarPedido(pedido);

        return PedidoMapper.mapearParaPedidoDTO(pedidoSalvo);
    }

}
