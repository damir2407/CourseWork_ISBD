package com.oasis.oasisapp.dto;


public class LibraryDataResponse {
    private String game_name;


    private String picture_shop;

    private String game_url;

    private String last_run_date;

    public LibraryDataResponse(String game_name, String picture_shop, String last_run_date, String game_url) {
        this.game_name = game_name;
        this.picture_shop = picture_shop;
        this.last_run_date = last_run_date;
        this.game_url=game_url;
    }

    public String getLast_run_date() {
        return last_run_date;
    }

    public void setLast_run_date(String last_run_date) {
        this.last_run_date = last_run_date;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getPicture_shop() {
        return picture_shop;
    }

    public void setPicture_shop(String picture_shop) {
        this.picture_shop = picture_shop;
    }

    public String getGame_url() {
        return game_url;
    }

    public void setGame_url(String game_url) {
        this.game_url = game_url;
    }
}
