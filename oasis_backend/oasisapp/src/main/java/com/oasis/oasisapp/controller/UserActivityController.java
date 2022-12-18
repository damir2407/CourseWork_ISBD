package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.AuthTokenFilter;
import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.*;
import com.oasis.oasisapp.exception.UserActivityException;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.model.UserActivity;
import com.oasis.oasisapp.service.UserActivityService;
import com.oasis.oasisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Validated
@RestController
@RequestMapping("/oasis/user_activity")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserActivityController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private UserActivityService userActivityService;

    @PostMapping("/activity_submit")
    public ResponseEntity<?> submitActivity(@Valid @RequestBody ActivityRequest activityRequest, HttpServletRequest httpServletRequest) {
        String login = jwtUtils.getLoginFromJwtToken(authTokenFilter.parseJwt(httpServletRequest));
        User user = userService.getUserByLogin(login);
        userActivityService.saveUserActivity(user, activityRequest.getText());
        return ResponseEntity.ok(new MessageResponse("Запись успешно опубликована!"));
    }

    @PostMapping("/get_all_activities")
    public ResponseEntity<?> getAllActivities(@RequestBody LoginRequest loginRequest) throws UserActivityException {
        List<UserActivity> userActivityList = userActivityService.getAllActivities(loginRequest.getLogin());
        List<UserActivityResponse> userActivityResponseList = new ArrayList<>();
        userActivityList.forEach(userActivity -> {
            userActivityResponseList.add(new UserActivityResponse(userActivity.getSend_date().toString().substring(0,
                    userActivity.getSend_date().toString().indexOf('.')),
                    userActivity.getActivity_text()));
        });

        userActivityResponseList.sort(Comparator.comparing(UserActivityResponse::getSend_date));
        Collections.reverse(userActivityResponseList);
        return ResponseEntity.ok(userActivityResponseList);

    }


}
