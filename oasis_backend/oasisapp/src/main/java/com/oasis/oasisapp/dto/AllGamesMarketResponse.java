package com.oasis.oasisapp.dto;

public class AllGamesMarketResponse {
    private String game_name;
    private String game_picture;


    public AllGamesMarketResponse(String game_name, String game_picture) {
        this.game_name = game_name;
        this.game_picture = game_picture;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_picture() {
        return game_picture;
    }

    public void setGame_picture(String game_picture) {
        this.game_picture = game_picture;
    }
}
