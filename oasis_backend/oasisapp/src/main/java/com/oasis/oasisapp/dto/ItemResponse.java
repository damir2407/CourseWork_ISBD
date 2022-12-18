package com.oasis.oasisapp.dto;


public class ItemResponse {
    private String item_picture;
    private String item_name;
    private String game_name;
    private String rarity;
    private Integer amount;

    private String game_picture;

    public ItemResponse(String item_picture, String item_name, String game_name, String rarity, Integer amount, String game_picture) {
        this.item_picture = item_picture;
        this.item_name = item_name;
        this.game_name = game_name;
        this.rarity = rarity;
        this.amount = amount;
        this.game_picture=game_picture;
    }

    public String getItem_picture() {
        return item_picture;
    }

    public void setItem_picture(String item_picture) {
        this.item_picture = item_picture;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGame_picture() {
        return game_picture;
    }

    public void setGame_picture(String game_picture) {
        this.game_picture = game_picture;
    }
}
