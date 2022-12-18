package com.oasis.oasisapp.dto;

import java.util.List;
import java.util.Set;

public class ShopDataResponse {
    private String game_name;

    private Double price;

    private String picture_shop;

    private List<String> genres;

    public ShopDataResponse(String game_name, Double price, String picture_shop, List<String> genres) {
        this.game_name = game_name;
        this.price = price;
        this.picture_shop = picture_shop;
        this.genres=genres;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture_shop() {
        return picture_shop;
    }

    public void setPicture_shop(String picture_shop) {
        this.picture_shop = picture_shop;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
