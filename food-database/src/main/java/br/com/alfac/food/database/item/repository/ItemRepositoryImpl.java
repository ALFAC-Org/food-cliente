package br.com.alfac.food.database.item.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.alfac.food.core.application.item.dto.ItemDTO;
import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.database.item.entity.ItemEntity;
import br.com.alfac.food.database.item.mapper.ItemEntityMapper;
import jakarta.persistence.EntityNotFoundException;

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
        List<ItemEntity> itemEntities = itemEntityRepository.findByCategoria(categoria);

        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities) {
            Item item = itemEntityMapper.toDomain(itemEntity);
            items.add(item);
        }

        return items;
    }

    @Override
    public Optional<Item> consultarItemPorId(Long id) {
        Optional<Item> itemOpt = Optional.empty();

        Optional<ItemEntity> itemEntityOpt = itemEntityRepository.findById(id);

        if (itemEntityOpt.isPresent()) {
            ItemEntity itemEntity = itemEntityOpt.get();

            Item item = itemEntityMapper.toDomain(itemEntity);

            itemOpt = Optional.of(item);
        }

        return itemOpt;
    }

    @Override
    public Item atualizarItem(Long id, ItemDTO item) {
        ItemEntity managedItemEntity = itemEntityRepository.findById(id).orElse(null);

        if (managedItemEntity != null) {
            managedItemEntity.setNome(item.getNome());
            // managedItemEntity.setPreco(item.getPreco());
            // managedItemEntity.setCategoria(item.getCategoria().toString());
            try {
                itemEntityRepository.save(managedItemEntity);
            } catch (Exception e) {
                System.out.println(e);
            }
            return itemEntityMapper.toDomain(managedItemEntity);
        } else {
            throw new EntityNotFoundException("Item não encontrado para o id informado");
        }
    }

    @Transactional
    @Override
    public Item excluirItem(Long id) {
        ItemEntity managedItemEntity = itemEntityRepository.findById(id).orElse(null);

        if (managedItemEntity != null) {
            itemEntityRepository.delete(managedItemEntity);
            return itemEntityMapper.toDomain(managedItemEntity);
        } else {
            throw new EntityNotFoundException("Item não encontrado para o id informado");
        }
    }
}
