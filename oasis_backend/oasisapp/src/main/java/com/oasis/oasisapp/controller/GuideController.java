package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.GuideRequest;
import com.oasis.oasisapp.dto.GuidesResponse;
import com.oasis.oasisapp.dto.LastGamesResponse;
import com.oasis.oasisapp.dto.SelectedGameGuideRequest;
import com.oasis.oasisapp.exception.GameDataException;
import com.oasis.oasisapp.exception.GuideDataException;
import com.oasis.oasisapp.exception.InventoryDataException;
import com.oasis.oasisapp.exception.LibraryDataException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Guide;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Validated
@RestController
@RequestMapping("/oasis/guide")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GuideController {

    @Autowired
    private GameService gameService;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/get_guides_by_selected_game")
    public ResponseEntity<?> getGamesBySelectedGame(@RequestBody SelectedGameGuideRequest selectedGameGuideRequest) throws SQLException, GuideDataException {
        List<Guide> guideList;
        if (selectedGameGuideRequest.getSelected_game_to_show().isEmpty()) guideList = guideService.getAllGuides();
        else guideList = guideService.getAllGuidesByGameName(selectedGameGuideRequest.getSelected_game_to_show());


        List<GuidesResponse> guidesResponses = new ArrayList<>();
        guideList.forEach(guide -> {
            guidesResponses.add(new GuidesResponse(guide.getUser().getLogin(),
                    guide.getSend_date().toString().substring(0,
                            guide.getSend_date().toString().indexOf('.')),
                    guide.getGuide_text(),
                    shopService.getGamePicture(guide.getGame().getName())
                    , guide.getGame().getName()));
        });
        guidesResponses.sort(Comparator.comparing(GuidesResponse::getSend_date));
        Collections.reverse(guidesResponses);

        return ResponseEntity.ok(guidesResponses);
    }




    @PostMapping("/add_guide")
    public ResponseEntity<?> addGuide(@Valid @RequestBody GuideRequest guideRequest, HttpServletRequest httpServletRequest) throws GameDataException, SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        User user = userService.getUserByLogin(login);
        Game game = gameService.getGameByName(guideRequest.getGame_name());
        guideService.saveGuide(user, game, guideRequest.getGuide_text());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
