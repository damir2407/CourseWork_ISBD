package com.oasis.oasisapp.model;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Game game;

    private Double price;

    private String description;

    private String picture_cover;

    private String picture_shop;

    private String picture_gameplay_1;

    private String picture_gameplay_2;

    private String picture_gameplay_3;

    public Shop(Game game, Double price, String description, String picture_cover, String picture_shop,
                String picture_gameplay_1, String picture_gameplay_2, String picture_gameplay_3) {
        this.game = game;
        this.price = price;
        this.description = description;
        this.picture_cover = picture_cover;
        this.picture_shop = picture_shop;
        this.picture_gameplay_1 = picture_gameplay_1;
        this.picture_gameplay_2 = picture_gameplay_2;
        this.picture_gameplay_3 = picture_gameplay_3;
    }

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
