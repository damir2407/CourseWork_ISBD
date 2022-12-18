package com.oasis.oasisapp.service;

import com.oasis.oasisapp.exception.ItemAlreadyExistException;
import com.oasis.oasisapp.exception.ItemDataException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void saveItem(Game game, String name, String rarity, String item_url) throws ItemAlreadyExistException {
        if (itemRepository.existsItemByGameAndNameAndRarity(game.getId(), name, rarity).isPresent())
            throw new ItemAlreadyExistException("Вещь " + name + " с редкостью " + rarity + " уже существует у игры " + game.getName());

        itemRepository.save(new Item(game, name, rarity, item_url));
    }

    public Item getItemByGameNameItemNameAndRarity(Game game, String item_name, String rarity) throws ItemDataException {
        return itemRepository.existsItemByGameAndNameAndRarity(game.getId(), item_name, rarity).orElseThrow(() -> new ItemDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));

    }

}
