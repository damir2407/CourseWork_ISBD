package com.oasis.oasisapp.dto;

public class ItemBuyRequest {
    private String game_name;
    private String item_name;
    private String rarity;

    private Long market_id;

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Long getMarket_id() {
        return market_id;
    }

    public void setMarket_id(Long market_id) {
        this.market_id = market_id;
    }
}
