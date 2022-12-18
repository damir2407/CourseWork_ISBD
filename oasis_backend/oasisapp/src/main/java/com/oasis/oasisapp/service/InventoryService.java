package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.ItemResponse;
import com.oasis.oasisapp.exception.InventoryDataException;
import com.oasis.oasisapp.exception.ItemDataException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Inventory;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.InventoryRepository;
import com.oasis.oasisapp.repository.ItemRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    public int saveInventory(User user, Game game) throws ItemDataException {
        List<Item> listOfItems = itemRepository.getAllItemsByGameId(game.getId()).orElseThrow(() -> new ItemDataException("Произошла ошибка в ходе получения данных из вещей"));
        Item commonItem = null;
        Item rareItem = null;
        Item legendaryItem = null;

        if (listOfItems != null) {
            for (Item item : listOfItems) {
                if (item != null) {
                    switch (item.getRarity()) {
                        case "Обычная" -> commonItem = item;
                        case "Редкая" -> rareItem = item;
                        case "Легендарная" -> legendaryItem = item;
                        default -> {
                        }
                    }
                }
            }
        }


        double rnd = Math.random();
        System.out.println(rnd);

        if (rnd > 0.80) {

            if (rnd > 0.95) {

                if (legendaryItem != null) {
                    inventoryRepository.save(new Inventory(user, legendaryItem, 0));
                    return 1;
                } else if (rareItem != null) {
                    inventoryRepository.save(new Inventory(user, rareItem, 0));
                    return 1;
                } else if (commonItem != null) {
                    inventoryRepository.save(new Inventory(user, commonItem, 0));
                    return 1;
                }

            } else if (rareItem != null) {
                inventoryRepository.save(new Inventory(user, rareItem, 0));
                return 1;
            } else if (commonItem != null) {
                inventoryRepository.save(new Inventory(user, commonItem, 0));
                return 1;
            }
        } else if (commonItem != null) {
            inventoryRepository.save(new Inventory(user, commonItem, 0));
            return 1;
        }
        return 0;
    }

    public List<ItemResponse> getAllItems(String user_login) throws InventoryDataException {
        List<Inventory> allInventoryData = inventoryRepository.getAllByUserLogin(user_login).orElseThrow(() -> new InventoryDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        List<ItemResponse> itemResponses = new ArrayList<>();
        allInventoryData.forEach(inventory -> {
            itemResponses.add(new ItemResponse(inventory.getItem().getItem_url(),
                    inventory.getItem().getName(), inventory.getItem().getGame().getName(),
                    inventory.getItem().getRarity(), inventory.getAmount(),
                    shopRepository.getGameShopPictureByGameName(inventory.getItem().getGame().getName())));
        });
        return itemResponses;
    }

    public void saveItemToCustomer(User user, Item item) {
        inventoryRepository.save(new Inventory(user, item, 0));
    }

}
