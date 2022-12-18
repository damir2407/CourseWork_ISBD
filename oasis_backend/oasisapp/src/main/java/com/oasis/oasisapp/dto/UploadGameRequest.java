package com.oasis.oasisapp.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Set;

public class UploadGameRequest {

    @NotBlank(message = "Укажите название игры!")
    @Size(min = 1, max = 64, message = "Укажите название игры! От 1 до 64 символов")
    private String name;

    @NotBlank(message = "Укажите описание игры!")
    private String description;

    @NotNull(message = "Укажите жанр(ы)!")
    private Set<String> genres;

    @NotBlank(message = "Укажите ссылку на игру")
    @Size(max = 256, message = "Укажите ссылку на игру! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String game_url;


    @NotNull(message = "Укажите цену! Укажите цену равную 0$, если хотите выпустить бесплатную игру")
    @Min(value = 0, message = "Цена должна быть от 0$ до 500$")
    @Max(value = 500, message = "Цена должна быть от 0$ до 500$")
    private Double price;

    @NotBlank(message = "Укажите ссылку на картинку-обложку")
    @Size(max = 256, message = "Укажите ссылку на картинку-обложку! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String picture_cover;

    @NotBlank(message = "Укажите ссылку на картинку в магазине")
    @Size(max = 256, message = "Укажите ссылку на картинку в магазине! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String picture_shop;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 1")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 1! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String picture_gameplay_1;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 2")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 2! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String picture_gameplay_2;

    @NotBlank(message = "Укажите ссылку на картинку-геймплей 3")
    @Size(max = 256, message = "Укажите ссылку на картинку-геймплей 3! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String picture_gameplay_3;

    private String devLogin;


    @Size(max = 20, message = "Укажите название обычной вещи! До 20 символов")
    private String common_item_name;

    @Size(max = 256, message = "Укажите ссылку на картинку обычной вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String common_item_url;


    @Size(max = 20, message = "Укажите название редкой вещи! До 20 символов")
    private String rare_item_name;

    @Size(max = 256, message = "Укажите ссылку на картинку редкой вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String rare_item_url;

    @Size(max = 20, message = "Укажите название легендарной вещи! До 20 символов")
    private String legendary_item_name;

    @Size(max = 256, message = "Укажите ссылку на картинку легендарной вещи! До 256 символов")
    @URL(message = "Неверный формат ссылки!")
    private String legendary_item_url;

    public String getDevLogin() {
        return devLogin;
    }

    public void setDevLogin(String devLogin) {
        this.devLogin = devLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture_cover() {
        return picture_cover;
    }

    public void setPicture_cover(String picture_cover) {
        this.picture_cover = picture_cover;
    }

    public String getPicture_shop() {
        return picture_shop;
    }

    public void setPicture_shop(String picture_shop) {
        this.picture_shop = picture_shop;
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


    public String getGame_url() {
        return game_url;
    }

    public void setGame_url(String game_url) {
        this.game_url = game_url;
    }

    public String getCommon_item_name() {
        return common_item_name;
    }

    public void setCommon_item_name(String common_item_name) {
        this.common_item_name = common_item_name;
    }

    public String getCommon_item_url() {
        return common_item_url;
    }

    public void setCommon_item_url(String common_item_url) {
        this.common_item_url = common_item_url;
    }

    public String getRare_item_name() {
        return rare_item_name;
    }

    public void setRare_item_name(String rare_item_name) {
        this.rare_item_name = rare_item_name;
    }

    public String getRare_item_url() {
        return rare_item_url;
    }

    public void setRare_item_url(String rare_item_url) {
        this.rare_item_url = rare_item_url;
    }

    public String getLegendary_item_name() {
        return legendary_item_name;
    }

    public void setLegendary_item_name(String legendary_item_name) {
        this.legendary_item_name = legendary_item_name;
    }

    public String getLegendary_item_url() {
        return legendary_item_url;
    }

    public void setLegendary_item_url(String legendary_item_url) {
        this.legendary_item_url = legendary_item_url;
    }


}
