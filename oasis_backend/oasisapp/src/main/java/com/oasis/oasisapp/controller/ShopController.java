package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.dto.GameNameAndGenresRequest;
import com.oasis.oasisapp.exception.ShopDataException;
import com.oasis.oasisapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oasis/shop")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ShopController {
    @Autowired
    private ShopService shopService;


//    @GetMapping("/get_all_games")
//    public ResponseEntity<?> getAllGamesFromShop(HttpServletRequest httpServletRequest) throws ShopDataException {
//
//        return ResponseEntity.ok(shopService.getAllEntries());
//
//    }

//    @PostMapping("/get_by_genre")
//    public ResponseEntity<?> getGamesByGenre(@RequestBody GenreRequest genreRequest) throws ShopDataException {
//        return ResponseEntity.ok(shopService.getEntriesByGenres(genreRequest.getGenres()));
//    }
//
//    @PostMapping("/get_by_game_name")
//    public ResponseEntity<?> getGamesByName(@RequestBody GameNameRequest gameNameRequest) throws ShopDataException {
//        return ResponseEntity.ok(shopService.getEntriesByGameName(gameNameRequest.getGameName()));
//
//    }

    @PostMapping("/get_by_game_name_and_genres")
    public ResponseEntity<?> getGamesByNameAndGenres(@RequestBody GameNameAndGenresRequest gameNameAndGenresRequest) throws ShopDataException {
        return ResponseEntity.ok(shopService.getEntriesByGameNameAndGenres(gameNameAndGenresRequest.getGameName(),
                gameNameAndGenresRequest.getGenres()));

    }


}
