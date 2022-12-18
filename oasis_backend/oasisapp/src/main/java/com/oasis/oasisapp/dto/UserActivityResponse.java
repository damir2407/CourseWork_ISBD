package com.oasis.oasisapp.dto;

public class UserActivityResponse {

    public UserActivityResponse(String send_date, String text) {
        this.send_date = send_date;
        this.text = text;
    }

    private String send_date;
    private String text;

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
