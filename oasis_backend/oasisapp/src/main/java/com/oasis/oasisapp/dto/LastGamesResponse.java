package com.oasis.oasisapp.dto;

public class LastGamesResponse {
    private String shop_picture;
    private String game_name;
    private String last_enter_date;

    public LastGamesResponse(String shop_picture, String game_name, String last_enter_date) {
        this.shop_picture = shop_picture;
        this.game_name = game_name;
        this.last_enter_date = last_enter_date;
    }

    public String getShop_picture() {
        return shop_picture;
    }

    public void setShop_picture(String shop_picture) {
        this.shop_picture = shop_picture;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getLast_enter_date() {
        return last_enter_date;
    }

    public void setLast_enter_date(String last_enter_date) {
        this.last_enter_date = last_enter_date;
    }
}
