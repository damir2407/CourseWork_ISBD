package com.oasis.oasisapp.service;

import com.oasis.oasisapp.exception.ShopDataException;
import com.oasis.oasisapp.exception.UserActivityException;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.model.UserActivity;
import com.oasis.oasisapp.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserActivityService {
    @Autowired
    private UserActivityRepository userActivityRepository;

    public void saveUserActivity(User user, String text) {
        userActivityRepository.save(new UserActivity(user, text, new Timestamp(System.currentTimeMillis())));
    }

    public List<UserActivity> getAllActivities(String login) throws UserActivityException {
       return userActivityRepository.getAllByUserLogin(login).orElseThrow(() -> new UserActivityException("Произошла ошибка! Попробуйте перезагрузить страницу"));
    }
}
