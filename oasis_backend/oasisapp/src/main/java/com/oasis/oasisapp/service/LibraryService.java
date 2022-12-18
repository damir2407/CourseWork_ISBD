package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.LastGamesResponse;
import com.oasis.oasisapp.dto.LibraryDataResponse;
import com.oasis.oasisapp.exception.LibraryDataException;
import com.oasis.oasisapp.model.*;
import com.oasis.oasisapp.repository.LibraryRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private ShopRepository shopRepository;


    public void saveGameInUserLibrary(User user, Game game) {
        Library library = new Library(user, game);
        libraryRepository.save(library);
    }


    public List<LibraryDataResponse> getEntriesByGameName(String game_name, String user_login) throws LibraryDataException {
        List<Library> allLibraryEntries;
        if (game_name == null)
            allLibraryEntries = libraryRepository.getAllFromLibraryByUserLogin(user_login).orElseThrow(() -> new LibraryDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        else
            allLibraryEntries = libraryRepository.getAllFromLibraryByGameNameFilter(game_name, user_login).orElseThrow(() -> new LibraryDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));

        List<LibraryDataResponse> libraryDataResponses = new ArrayList<>();

        allLibraryEntries.forEach(library -> {

            String game_name_to_send = library.getGame().getName();
            String game_picture_to_send = libraryRepository.getGameShopPictureByGameName(library.getGame().getName());
            String last_run_date_to_send;
            if (library.getLast_run_date() == null) last_run_date_to_send = "";
            else last_run_date_to_send = library.getLast_run_date().toString().substring(0,
                    library.getLast_run_date().toString().indexOf('.'));


            libraryDataResponses.add(new LibraryDataResponse(game_name_to_send,
                    game_picture_to_send,
                    last_run_date_to_send, library.getGame().getGame_url()));

        });

        return libraryDataResponses;
    }


    public void enterTheGame(String login, String game_name) throws SQLException {
        libraryRepository.enterInGame(login, game_name);
    }

    public Integer getCountOfUserGames(String user_login) {
        return libraryRepository.getCountOfGames(user_login);
    }


    public List<LastGamesResponse> getUserLastGames(String user_login) throws LibraryDataException {
        List<Library> allLibraryEntries = libraryRepository.getAllFromLibraryByUserLogin(user_login)
                .orElseThrow(() -> new LibraryDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        List<LastGamesResponse> lastGamesResponses = new ArrayList<>();
        allLibraryEntries.forEach(library -> {
            if (library.getLast_run_date() != null && lastGamesResponses.size() < 3) {
                lastGamesResponses.add(new LastGamesResponse
                        (shopRepository.getGameShopPictureByGameName(library.getGame().getName()),
                                library.getGame().getName(),
                                library.getLast_run_date().toString().substring(0,
                                        library.getLast_run_date().toString().indexOf('.'))));
            }
        });
        lastGamesResponses.sort(Comparator.comparing(LastGamesResponse::getLast_enter_date));
        Collections.reverse(lastGamesResponses);
        return lastGamesResponses;
    }

    public List<Game> getUserGames(String login) throws LibraryDataException {
        List<Library> libraryList;
        List<Game> userGames = new ArrayList<>();
        libraryList = libraryRepository.getAllFromLibraryByUserLogin(login).orElseThrow(() -> new LibraryDataException("Произошла ошибка! Попробуйте перезагрузить страницу"));
        libraryList.forEach(library -> {
            userGames.add(library.getGame());
        });
        return userGames;
    }

}
