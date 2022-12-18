package com.oasis.oasisapp.dto;

public class SlotsResponse {
    private String item_name;
    private String item_picture;
    private String game_name;
    private String rarity;
    private Double price;

    private Long marketId;

    public SlotsResponse(String item_name, String item_picture, String game_name, String rarity, Double price, Long marketId) {
        this.item_name = item_name;
        this.item_picture = item_picture;
        this.game_name = game_name;
        this.rarity = rarity;
        this.price = price;
        this.marketId = marketId;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_picture() {
        return item_picture;
    }

    public void setItem_picture(String item_picture) {
        this.item_picture = item_picture;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }
}
