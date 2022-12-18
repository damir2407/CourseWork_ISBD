package com.oasis.oasisapp.dto;

public class GuidesResponse {
    private String user_login;
    private String send_date;
    private String guide_text;
    private String game_picture_url;
    private String game_name;


    public GuidesResponse(String user_login, String send_date, String guide_text, String game_picture_url, String game_name) {
        this.user_login = user_login;
        this.send_date = send_date;
        this.guide_text = guide_text;
        this.game_picture_url = game_picture_url;
        this.game_name = game_name;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getGuide_text() {
        return guide_text;
    }

    public void setGuide_text(String guide_text) {
        this.guide_text = guide_text;
    }

    public String getGame_picture_url() {
        return game_picture_url;
    }

    public void setGame_picture_url(String game_picture_url) {
        this.game_picture_url = game_picture_url;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
}
