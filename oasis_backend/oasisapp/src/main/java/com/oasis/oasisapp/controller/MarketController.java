package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.GetSlotsRequest;
import com.oasis.oasisapp.dto.ItemBuyRequest;
import com.oasis.oasisapp.dto.ItemSellRequest;
import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.exception.GameDataException;
import com.oasis.oasisapp.exception.ItemDataException;
import com.oasis.oasisapp.exception.MarketDataException;
import com.oasis.oasisapp.exception.NotEnoughBalanceException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.Market;
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

@Validated
@RestController
@RequestMapping("/oasis/market")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MarketController {

    @Autowired
    private UserService userService;
    @Autowired
    private MarketService marketService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private GameService gameService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private AuthTokenFilter authTokenFilter;

    @PostMapping("/sell_item")
    public ResponseEntity<?> sellItem(@Valid @RequestBody ItemSellRequest itemSellRequest, HttpServletRequest httpServletRequest) throws GameDataException, ItemDataException, SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        User user = userService.getUserByLogin(login);
        Game game = gameService.getGameByName(itemSellRequest.getGame_name());
        Item item = itemService.getItemByGameNameItemNameAndRarity(game, itemSellRequest.getItem_name()
                , itemSellRequest.getRarity());

        marketService.save(user, item, itemSellRequest.getPrice());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get_all_slots")
    public ResponseEntity<?> getAllSlots(@RequestBody GetSlotsRequest getSlotsRequest) throws SQLException, MarketDataException {
        return ResponseEntity.ok(marketService.getEntriesByGameNameAndItemName(getSlotsRequest.getItem_name()
                , getSlotsRequest.getGame_name()));

    }


    @PostMapping("/buy_item")
    public ResponseEntity<?> buyItem(@RequestBody ItemBuyRequest itemBuyRequest, HttpServletRequest httpServletRequest) throws SQLException, MarketDataException, GameDataException, ItemDataException, NotEnoughBalanceException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        User user = userService.getUserByLogin(login);
        Market market = marketService.getMarketById(itemBuyRequest.getMarket_id());


        if (user.getWallet().getBalance() < market.getPrice())
            throw new NotEnoughBalanceException("У вас недостаточно средств для покупки этой вещи!");

        Game game = gameService.getGameByName(itemBuyRequest.getGame_name());
        Item item = itemService.getItemByGameNameItemNameAndRarity(game,
                itemBuyRequest.getItem_name(), itemBuyRequest.getRarity());
        inventoryService.saveItemToCustomer(user, item);

        userService.chargeBalanceCustomer(login, market.getPrice());
        userService.replenishBalanceSeller(market.getUser().getLogin(), market.getPrice());

        marketService.deleteSlot(market.getId());

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
