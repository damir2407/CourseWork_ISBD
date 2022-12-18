package com.oasis.oasisapp.service;


import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.UserRepository;
import com.oasis.oasisapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkUserOnExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public User getUserByLogin(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с логином " + login + " не существует"));
    }

    public String getUserStatus(String login) {
        return getUserByLogin(login).getStatus();
    }

    public LocalDate getUserLastLoginDate(String login) {
        return getUserByLogin(login).getLastLoginDate();
    }

    public LocalDate getRegistrationDate(String login) {
        return getUserByLogin(login).getRegistrationDate();
    }


    public void logoutAsUser(String login) throws SQLException {
        userRepository.logoutFromUser(login);
    }

    public void addBalance(String login, Double balance) throws SQLException {
        userRepository.replenishBalance(login, balance);
    }

    public Double getBalance(String login) throws SQLException {
       return userRepository.getBalance(login);
    }

    public void replenishBalanceSeller(String user_login, Double balance){
        userRepository.replenishBalanceSeller(user_login,balance);
    }

    public void chargeBalanceCustomer(String user_login, Double balance){
        userRepository.chargeBalanceCustomer(user_login,balance);
    }


}