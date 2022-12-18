package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.GameNameRequest;
import com.oasis.oasisapp.dto.LoginRequest;
import com.oasis.oasisapp.exception.InventoryDataException;
import com.oasis.oasisapp.exception.LibraryDataException;
import com.oasis.oasisapp.exception.ShopDataException;
import com.oasis.oasisapp.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController
@RequestMapping("/oasis/library")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LibraryController {

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/get_by_game_name_library")
    public ResponseEntity<?> getGamesByName(@RequestBody GameNameRequest gameNameRequest, HttpServletRequest httpServletRequest) throws LibraryDataException, SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));

        return ResponseEntity.ok(libraryService.getEntriesByGameName(gameNameRequest.getGame_name(), login));
    }

    @PostMapping("/enter_game")
    public ResponseEntity<?> enterGame(@RequestBody GameNameRequest gameNameRequest, HttpServletRequest httpServletRequest) throws SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        libraryService.enterTheGame(login, gameNameRequest.getGame_name());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get_all_games_count")
    public ResponseEntity<?> getGamesCount(@RequestBody LoginRequest loginRequest) throws SQLException {
        return ResponseEntity.ok(libraryService.getCountOfUserGames(loginRequest.getLogin()));
    }

    @PostMapping("/get_last_games")
    public ResponseEntity<?> getLastGames(@RequestBody LoginRequest loginRequest) throws SQLException, LibraryDataException {
        return ResponseEntity.ok(libraryService.getUserLastGames(loginRequest.getLogin()));
    }


}
