package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.AllGamesMarketResponse;
import com.oasis.oasisapp.dto.GameInfoResponse;
import com.oasis.oasisapp.exception.*;
import com.oasis.oasisapp.model.*;
import com.oasis.oasisapp.repository.GameRepository;
import com.oasis.oasisapp.repository.GenreRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import com.oasis.oasisapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    public Game saveGame(String name, Set<String> reqGenres, String devLogin, String game_url) throws SQLException, GenreNotFoundException, GameAlreadyExistException, UsernameNotFoundException {

        if (gameRepository.getByNameInAnyRegister(name).isPresent() || gameRepository.getByGameUrlInAnyRegister(game_url).isPresent())
            throw new GameAlreadyExistException("Данная игра уже опубликована!");


        Set<Genre> genres = new HashSet<>();

        reqGenres.forEach(r -> {
            Genre genre = genreRepository
                    .findByName(r)
                    .orElseThrow(() -> new GenreNotFoundException("Жанр " + r + " не найден"));
            genres.add(genre);
        });

        Game game = new Game(name, LocalDate.now());
        game.setGenres(genres);

        User dev = userRepository.findByLogin(devLogin).orElseThrow(() -> new UsernameNotFoundException("Данный пользователь не зарегистрирован в системе!"));
        game.setUser(dev);
        game.setGame_url(game_url);
        gameRepository.save(game);

        return game;
    }

    public GameInfoResponse getGameInfo(String game_name) throws ShopDataException {
        Shop shop = shopRepository.getShopByGameName(game_name).orElseThrow(() -> new ShopDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        String game_name_resp = shop.getGame().getName();
        Double price_resp = shop.getPrice();
        List<String> genres_resp = shop.getGame().getGenres().stream()
                .map(Genre::getName).toList();
        String development_date_resp = shop.getGame().getDevelopment_date().toString();
        String dev_login_resp = shop.getGame().getUser().getLogin();
        String game_description_resp = shop.getDescription();
        String picture_cover_resp = shop.getPicture_cover();
        String picture_gameplay_1_resp = shop.getPicture_gameplay_1();
        String picture_gameplay_2_resp = shop.getPicture_gameplay_2();
        String picture_gameplay_3_resp = shop.getPicture_gameplay_3();

        return new GameInfoResponse(game_name_resp, price_resp, genres_resp,
                development_date_resp, dev_login_resp, game_description_resp,
                picture_cover_resp, picture_gameplay_1_resp,
                picture_gameplay_2_resp, picture_gameplay_3_resp);

    }

    public Game getGameByName(String game_name) throws GameDataException {
        return gameRepository.getGameByName(game_name).orElseThrow(() -> new GameDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }

    public boolean checkGameOnExist(String game_name) {
        return gameRepository.existsByName(game_name);
    }

    public List<Game> getAllGames() throws GameDataException {
        return gameRepository.getAllGames().orElseThrow(() -> new GameDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }


}
