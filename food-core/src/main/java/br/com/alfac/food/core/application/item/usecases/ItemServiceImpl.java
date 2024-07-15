package br.com.alfac.food.core.application.item.usecases;

import java.util.List;
import java.util.Optional;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.gateways.ItemRepository;
import br.com.alfac.food.core.application.item.gateways.ItemService;
import br.com.alfac.food.core.application.item.mappers.ItemMapper;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.item.ItemError;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> consultarItens() throws FoodException {
        List<Item> itemList = itemRepository.consultarItens();

        return ItemMapper.mapearParaItemDTOList(itemList);
    }

    @Override
    public List<ItemDTO> consultarItensPorCategoria(CategoriaItem categoria) throws FoodException {
        List<Item> itemList = itemRepository.consultarItensPorCategoria(categoria);

        if (itemList == null || itemList.isEmpty()) {
            throw new FoodException(ItemError.CATEGORIA_SEM_ITENS);
        }

        return ItemMapper.mapearParaItemDTOList(itemList);
    }

    @Override
    public ItemDTO consultarItemPorId(Long id) throws FoodException {
        Optional<Item> itemOpt = itemRepository.consultarItemPorId(id);

        Item item = itemOpt.orElseThrow(() -> new FoodException(ItemError.ITEM_NAO_ENCONTRADO));

        return ItemMapper.mapearParaItemDTO(item);
    }

    @Override
    public ItemDTO cadastrarItem(ItemDTO itemDTO) throws FoodException {
        Item item = new Item();

        item.setNome(itemDTO.getNome());
        item.setPreco(itemDTO.getPreco());
        item.setCategoria(itemDTO.getCategoria());

        Item itemCriado = itemRepository.cadastrarItem(item);

        return ItemMapper.mapearParaItemDTO(itemCriado);
    }

    @Override
    public ItemDTO atualizarItem(Long id, ItemDTO item) throws FoodException {
        Item itemAtualizado = itemRepository.atualizarItem(id, item);

        return ItemMapper.mapearParaItemDTO(itemAtualizado);
    }

    @Override
    public ItemDTO excluirItem(Long id) throws FoodException {
        Item item = itemRepository.excluirItem(id);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setNome(item.getNome());
        itemDTO.setPreco(item.getPreco());
        itemDTO.setCategoria(item.getCategoria());

        return itemDTO;
    }
}
