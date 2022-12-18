package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.ItemResponse;
import com.oasis.oasisapp.exception.InventoryDataException;
import com.oasis.oasisapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController
@RequestMapping("/oasis/inventory")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;


    @GetMapping("/get_all_items")
    public ResponseEntity<?> getAllItems(HttpServletRequest httpServletRequest) throws SQLException, InventoryDataException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));

        return ResponseEntity.ok(inventoryService.getAllItems(login));
    }

}
