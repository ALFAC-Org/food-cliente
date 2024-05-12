package br.com.alfac.food.database.item.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.database.item.entity.ItemEntity;
import br.com.alfac.food.database.item.mapper.ItemEntityMapper;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    public ItemRepositoryImpl(final ItemEntityRepository itemEntityRepository, final ItemEntityMapper itemMapper) {
        this.itemEntityRepository = itemEntityRepository;
        this.itemEntityMapper = itemMapper;
    }

    @Override
    public List<Item> consultarItens() {
        List<ItemEntity> itemEntities = itemEntityRepository.findAll();

        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities) {
            Item item = itemEntityMapper.toDomain(itemEntity);
            items.add(item);
        }

        return items;
    }

    @Override
    public List<Item> consultarItensPorCategoria(CategoriaItem categoria) {
        // TODO:[Fraga] - Precisamos converter para string?
        List<ItemEntity> itemEntities = itemEntityRepository.findByCategoria(categoria.toString());

        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities) {
            Item item = itemEntityMapper.toDomain(itemEntity);
            items.add(item);
        }

        return items;
    }

    @Override
    public Optional<Item> consultarItemPorId(String id) {
        Optional<Item> itemOpt = Optional.empty();

        Optional<ItemEntity> itemEntityOpt = itemEntityRepository.findById(id);

        if (itemEntityOpt.isPresent()) {
            ItemEntity itemEntity = itemEntityOpt.get();

            Item item = itemEntityMapper.toDomain(itemEntity);

            itemOpt = Optional.of(item);
        }

        return itemOpt;
    }
}
