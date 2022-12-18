package com.oasis.oasisapp.controller;


import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;


import com.oasis.oasisapp.dto.*;
import com.oasis.oasisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;


@Validated
@RestController
@RequestMapping("/oasis/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private UserService userService;

    @GetMapping("/get_login")
    public ResponseEntity<?> getUserLogin(HttpServletRequest httpServletRequest) {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        return ResponseEntity.ok(new MessageResponse(login));
    }


//    @PostMapping("/get_status")
//    public ResponseEntity<?> getUserStatus(@RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {
//
//        String login = loginRequest.getLogin();
//        String status = userService.getUserStatus(login);
//
//        return ResponseEntity.ok(new MessageResponse(status));
//
//
//    }


    @PostMapping("/check-user-on-exist")
    public ResponseEntity<?> checkUserLogin(@RequestBody LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        if (userService.checkUserOnExist(login)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//
//
//    @PostMapping("/get_last_login_date")
//    public ResponseEntity<?> getLastLoginDate(@RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {
//
//        String login = loginRequest.getLogin();
//        OffsetDateTime lastLoginDate = userService.getUserLastLoginDate(login);
//        if (lastLoginDate == null) return ResponseEntity.ok(new MessageResponse(""));
//        else return ResponseEntity.ok(new MessageResponse(lastLoginDate.toString()));
//
//    }
//
//
//    @PostMapping("/get_registration_date")
//    public ResponseEntity<?> getRegistrationDate(@RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {
//
//        String login = loginRequest.getLogin();
//        LocalDate registrationDate = userService.getRegistrationDate(login);
//        return ResponseEntity.ok(new MessageResponse(registrationDate.toString()));
//
//    }


    @PostMapping("/get_status_and_dates")
    public ResponseEntity<?> getStatusAndDates(@RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {
        String login = loginRequest.getLogin();
        String status = userService.getUserStatus(login);

        LocalDate lastLoginDate = userService.getUserLastLoginDate(login);
        String lastLoginDateToSend;
        if (lastLoginDate == null) lastLoginDateToSend = "";
        else lastLoginDateToSend = lastLoginDate.toString();

        String registrationDate = userService.getRegistrationDate(login).toString();

        return ResponseEntity.ok(new UserDataResponse(status, lastLoginDateToSend, registrationDate));
    }


    @PostMapping("/logout_user")
    public ResponseEntity<?> logoutFromUser(@RequestBody LoginRequest loginRequest) throws SQLException {
        String login = loginRequest.getLogin();
        userService.logoutAsUser(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add_money")
    public ResponseEntity<?> addBalance(@Valid @RequestBody BalanceRequest balanceRequest, HttpServletRequest httpServletRequest) throws SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        userService.addBalance(login, balanceRequest.getBalance());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_balance")
    public ResponseEntity<?> getUserBalance(HttpServletRequest httpServletRequest) throws SQLException {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        return ResponseEntity.ok(new BalanceResponse(userService.getBalance(login)));
    }


}