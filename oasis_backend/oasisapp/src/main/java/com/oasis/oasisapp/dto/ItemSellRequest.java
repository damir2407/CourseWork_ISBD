package com.oasis.oasisapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemSellRequest {
    private String game_name;
    private String item_name;
    private String rarity;


    @NotNull(message = "Укажите цену!")
    @Min(value = 1, message = "Цена должна быть от 1$ до 500$")
    @Max(value = 500, message = "Цена должна быть от 1$ до 500$")
    private Double price;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
