package br.com.alfac.food.database.item.repository;

import br.com.alfac.food.core.application.item.ports.ItemRepository;
import br.com.alfac.food.database.item.mapper.ItemEntityMapper;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemRepositoryImpl implements ItemRepository {


    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    public ItemRepositoryImpl(final ItemEntityRepository itemEntityRepository, final ItemEntityMapper itemMapper) {
        this.itemEntityRepository = itemEntityRepository;
        this.itemEntityMapper = itemMapper;
    }

}
