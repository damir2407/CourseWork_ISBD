package com.oasis.oasisapp.controller;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.dto.UploadGameRequest;
import com.oasis.oasisapp.exception.GameAlreadyExistException;
import com.oasis.oasisapp.exception.GenreNotFoundException;
import com.oasis.oasisapp.exception.ItemAlreadyExistException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.service.GameService;
import com.oasis.oasisapp.service.ItemService;
import com.oasis.oasisapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Validated
@RestController
@RequestMapping("/oasis/dev")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DevController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ItemService itemService;


    @PostMapping("/upload_game")
    public ResponseEntity<?> uploadGame(@Valid @RequestBody UploadGameRequest uploadGameRequest) throws SQLException, GenreNotFoundException, GameAlreadyExistException, InvalidFormatException, ItemAlreadyExistException {

        Game game = gameService.saveGame(uploadGameRequest.getName(), uploadGameRequest.getGenres(), uploadGameRequest.getDevLogin(), uploadGameRequest.getGame_url());
        shopService.saveShop(game, uploadGameRequest.getPrice(), uploadGameRequest.getDescription(),
                uploadGameRequest.getPicture_cover(), uploadGameRequest.getPicture_shop(), uploadGameRequest.getPicture_gameplay_1(),
                uploadGameRequest.getPicture_gameplay_2(), uploadGameRequest.getPicture_gameplay_3());

        if (!uploadGameRequest.getCommon_item_name().isEmpty() && !uploadGameRequest.getCommon_item_url().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getCommon_item_name(),
                    "Обычная", uploadGameRequest.getCommon_item_url());

        if (!uploadGameRequest.getRare_item_name().isEmpty() && !uploadGameRequest.getRare_item_url().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getRare_item_name(),
                    "Редкая", uploadGameRequest.getRare_item_url());

        if (!uploadGameRequest.getLegendary_item_name().isEmpty() && !uploadGameRequest.getLegendary_item_url().isEmpty())
            itemService.saveItem(game, uploadGameRequest.getLegendary_item_name(),
                    "Легендарная", uploadGameRequest.getLegendary_item_url());

        return ResponseEntity.ok(new MessageResponse("Игра успешно загружена!"));

    }
}
