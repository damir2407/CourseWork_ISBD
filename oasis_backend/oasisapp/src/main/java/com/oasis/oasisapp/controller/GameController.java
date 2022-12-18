package com.oasis.oasisapp.controller;


import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.AllGamesMarketResponse;
import com.oasis.oasisapp.dto.GameNameRequest;
import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.exception.*;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/oasis/game")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/get_game_info")
    public ResponseEntity<?> getGameInfo(@RequestBody GameNameRequest gameNameRequest) throws SQLException, ShopDataException {
        return ResponseEntity.ok(gameService.getGameInfo(gameNameRequest.getGame_name()));
    }


    @PostMapping("/check-game-on-exist")
    public ResponseEntity<?> checkGameName(@RequestBody GameNameRequest gameNameRequest) {
        if (gameService.checkGameOnExist(gameNameRequest.getGame_name())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/buy_game")
    public ResponseEntity<?> buyGame(@RequestBody GameNameRequest gameNameRequest, HttpServletRequest httpServletRequest) throws GameDataException, ItemAlreadyExistException, ItemDataException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        Game game = gameService.getGameByName(gameNameRequest.getGame_name());
        User user = userService.getUserByLogin(login);
        libraryService.saveGameInUserLibrary(user, game);

        int status = inventoryService.saveInventory(user, game);
        if (status == 1) return ResponseEntity.ok(1);
        else return ResponseEntity.ok(0);
    }

    @GetMapping("/get_all_games")
    public ResponseEntity<?> getAllGames(HttpServletRequest httpServletRequest) throws SQLException, GameDataException {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/get_all_games_by_login")
    public ResponseEntity<?> getAllGamesByLogin(HttpServletRequest httpServletRequest) throws SQLException, LibraryDataException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        return ResponseEntity.ok(libraryService.getUserGames(login));
    }

    @GetMapping("/get_all_games_for_market")
    public ResponseEntity<?> getAllGamesForMarket(HttpServletRequest httpServletRequest) throws SQLException, GameDataException {
        List<Game> allGames = gameService.getAllGames();
        List<AllGamesMarketResponse> allGamesMarketResponses = new ArrayList<>();
        allGames.forEach(game -> {
            allGamesMarketResponses.add(new AllGamesMarketResponse(game.getName(),
                    shopService.getGamePicture(game.getName())));
        });
        return ResponseEntity.ok(allGamesMarketResponses);


    }


}
