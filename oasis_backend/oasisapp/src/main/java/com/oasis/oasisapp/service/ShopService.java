package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.ShopDataResponse;
import com.oasis.oasisapp.exception.GameAlreadyExistException;
import com.oasis.oasisapp.exception.ShopDataException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Genre;
import com.oasis.oasisapp.model.Shop;
import com.oasis.oasisapp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public void saveShop(Game game, Double price, String description,
                         String picture_cover, String picture_shop, String picture_gameplay_1,
                         String picture_gameplay_2, String picture_gameplay_3) throws SQLException, GameAlreadyExistException {

        if (shopRepository.existsByGame(game))
            throw new GameAlreadyExistException("Данная игра уже опубликована!");
        shopRepository.save(new Shop(game, price, description, picture_cover, picture_shop,
                picture_gameplay_1, picture_gameplay_2, picture_gameplay_3));

    }
//
//    public List<ShopDataResponse> getAllEntries() throws ShopDataException {
//        List<Shop> allShopEntries = shopRepository.getAllFromShop().orElseThrow(() -> new ShopDataException("Произошла ошибка в ходе получения данных из магазина"));
//
//        List<ShopDataResponse> shopDataResponses = new ArrayList<>();
//
//        allShopEntries.forEach(shop -> {
//            Set<String> genresResponse = new HashSet<>();
//
//            shop.getGame().getGenres().forEach(genre_of_game -> {
//                genresResponse.add(genre_of_game.getName());
//            });
//
//            ShopDataResponse shopDataResponse = new ShopDataResponse(shop.getGame().getName(), shop.getPrice(),
//                    shop.getPicture_shop(), genresResponse.stream().toList());
//            shopDataResponses.add(shopDataResponse);
//        });
//        return shopDataResponses;
//    }


//    public List<ShopDataResponse> getEntriesByGenres(List<String> genresReq) throws ShopDataException {
//        List<Shop> allShopEntries = shopRepository.getAllFromShop().orElseThrow(() -> new ShopDataException("Произошла ошибка в ходе получения данных из магазина"));
//        List<ShopDataResponse> shopDataResponses = new ArrayList<>();
//
//
//        allShopEntries.forEach(shop -> {
//
//            List<String> genresResponse = shop.getGame().getGenres().stream()
//                    .map(Genre::getName).toList();
////           если не выбраны жанры в фильтрах, то просто верну все игры с магазина
//            if (genresReq.isEmpty()) {
//                shopDataResponses.add(new ShopDataResponse(shop.getGame().getName(), shop.getPrice(),
//                        shop.getPicture_shop(), genresResponse.stream().toList()));
//
////          иначе выбираю лишь те игры, жанры которых подходят
//            } else if (new ArrayList<>(genresResponse).containsAll(genresReq)) {
//                shopDataResponses.add(new ShopDataResponse(shop.getGame().getName(), shop.getPrice(),
//                        shop.getPicture_shop(), genresResponse.stream().toList()));
//            }
//        });
//
//        return shopDataResponses;
//    }


    public List<ShopDataResponse> getEntriesByGameNameAndGenres(String game_name, List<String> genresReq) throws ShopDataException {
        List<Shop> allShopEntries;
        if (game_name.isEmpty())
            allShopEntries = shopRepository.getAllFromShop().orElseThrow(() -> new ShopDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        else
            allShopEntries = shopRepository.getAllFromShopByGameNameFilter(game_name).orElseThrow(() -> new ShopDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));

        List<ShopDataResponse> shopDataResponses = new ArrayList<>();

        allShopEntries.forEach(shop -> {

            List<String> genresResponse = shop.getGame().getGenres().stream()
                    .map(Genre::getName).toList();

            if (genresReq.isEmpty()) {
                shopDataResponses.add(new ShopDataResponse(shop.getGame().getName(), shop.getPrice(),
                        shop.getPicture_shop(), genresResponse.stream().toList()));
            } else if (new ArrayList<>(genresResponse).containsAll(genresReq)) {
                shopDataResponses.add(new ShopDataResponse(shop.getGame().getName(), shop.getPrice(),
                        shop.getPicture_shop(), genresResponse.stream().toList()));
            }

        });

        return shopDataResponses;
    }

    public String getGamePicture(String game_name){
       return shopRepository.getGameShopPictureByGameName(game_name);
    }


}
