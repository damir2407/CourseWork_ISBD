package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.ShopDataResponse;
import com.oasis.oasisapp.dto.SlotsResponse;
import com.oasis.oasisapp.dto.UserActivityResponse;
import com.oasis.oasisapp.exception.MarketDataException;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.Market;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

    public void save(User user, Item item, Double price) throws SQLException {
        marketRepository.save(new Market(user, item, price));
    }

    public Market getMarketById(Long id) throws MarketDataException {
        return marketRepository.getMarketById(id).orElseThrow(() -> new MarketDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }

    public List<SlotsResponse> getEntriesByGameNameAndItemName(String item_name, String game_name) throws MarketDataException {
        List<Market> marketList;
        if (item_name.isEmpty())
            marketList = marketRepository.getAllSlots().orElseThrow(() -> new MarketDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        else
            marketList = marketRepository.getAllFromMarketByItemNameFilter(item_name).orElseThrow(() -> new MarketDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));

        List<SlotsResponse> slotsResponses = new ArrayList<>();

        if (!game_name.isEmpty()) {
            marketList.forEach(market -> {
                if (market.getItem().getGame().getName().equals(game_name))
                    slotsResponses.add(new SlotsResponse(market.getItem().getName(),
                            market.getItem().getItem_url(),
                            market.getItem().getGame().getName(),
                            market.getItem().getRarity(),
                            market.getPrice(), market.getId()));
            });
        } else marketList.forEach(market -> {
            slotsResponses.add(new SlotsResponse(market.getItem().getName(),
                    market.getItem().getItem_url(),
                    market.getItem().getGame().getName(),
                    market.getItem().getRarity(),
                    market.getPrice(), market.getId()));
        });

        slotsResponses.sort(Comparator.comparing(SlotsResponse::getPrice));

        return slotsResponses;

    }

    public void deleteSlot(Long market_id) {
        marketRepository.deleteMarketById(market_id);
    }

}
