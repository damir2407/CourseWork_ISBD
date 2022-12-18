package com.oasis.oasisapp.dto;

import java.util.List;

public class GameInfoResponse {
    private String game_name;
    private Double price;
    private List<String> genres;
    private String development_date;
    private String dev_login;
    private String game_description;
    private String picture_cover;
    private String picture_gameplay_1;
    private String picture_gameplay_2;
    private String picture_gameplay_3;


    public GameInfoResponse(String game_name, Double price, List<String> genres,
                            String development_date, String dev_login, String game_description,
                            String picture_cover, String picture_gameplay_1, String picture_gameplay_2,
                            String picture_gameplay_3) {
        this.game_name = game_name;
        this.price = price;
        this.genres = genres;
        this.development_date = development_date;
        this.dev_login = dev_login;
        this.game_description = game_description;
        this.picture_cover = picture_cover;
        this.picture_gameplay_1 = picture_gameplay_1;
        this.picture_gameplay_2 = picture_gameplay_2;
        this.picture_gameplay_3 = picture_gameplay_3;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDevelopment_date() {
        return development_date;
    }

    public void setDevelopment_date(String development_date) {
        this.development_date = development_date;
    }

    public String getDev_login() {
        return dev_login;
    }

    public void setDev_login(String dev_login) {
        this.dev_login = dev_login;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public String getPicture_cover() {
        return picture_cover;
    }

    public void setPicture_cover(String picture_cover) {
        this.picture_cover = picture_cover;
    }

    public String getPicture_gameplay_1() {
        return picture_gameplay_1;
    }

    public void setPicture_gameplay_1(String picture_gameplay_1) {
        this.picture_gameplay_1 = picture_gameplay_1;
    }

    public String getPicture_gameplay_2() {
        return picture_gameplay_2;
    }

    public void setPicture_gameplay_2(String picture_gameplay_2) {
        this.picture_gameplay_2 = picture_gameplay_2;
    }

    public String getPicture_gameplay_3() {
        return picture_gameplay_3;
    }

    public void setPicture_gameplay_3(String picture_gameplay_3) {
        this.picture_gameplay_3 = picture_gameplay_3;
    }
}
