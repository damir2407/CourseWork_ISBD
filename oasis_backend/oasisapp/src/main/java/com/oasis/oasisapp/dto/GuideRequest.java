package com.oasis.oasisapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GuideRequest {

    @NotBlank(message = "Выберите игру для руководства!")
    @NotNull(message = "Выберите игру для руководства!")
    private String game_name;

    @NotBlank(message = "Обзор не может быть пустым!")
    @Size(min = 1, max = 1000, message = "Укажите обзор! От 1 до 1000 символов")
    private String guide_text;

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGuide_text() {
        return guide_text;
    }

    public void setGuide_text(String guide_text) {
        this.guide_text = guide_text;
    }
}
