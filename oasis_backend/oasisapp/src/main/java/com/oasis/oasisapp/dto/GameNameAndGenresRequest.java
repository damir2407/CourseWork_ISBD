package com.oasis.oasisapp.dto;

import java.util.List;

public class GameNameAndGenresRequest {
    private String gameName;
    private List<String> genres;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
