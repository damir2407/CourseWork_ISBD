package com.oasis.oasisapp.service;

import com.oasis.oasisapp.exception.RoleNotFoundException;
import com.oasis.oasisapp.exception.UserAlreadyExistException;
import com.oasis.oasisapp.model.ERole;
import com.oasis.oasisapp.model.Role;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.model.Wallet;
import com.oasis.oasisapp.repository.RoleRepository;
import com.oasis.oasisapp.repository.UserRepository;
import com.oasis.oasisapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WalletRepository walletRepository;


    public User saveUser(String login, String password, String email, Set<String> reqRoles) throws RoleNotFoundException, UserAlreadyExistException {

        if (userRepository.existsByLogin(login))
            throw new UserAlreadyExistException("Этот логин уже занят. Попробуйте другой");

        if (userRepository.existsByEmail(email))
            throw new UserAlreadyExistException("Эта электронная почта уже занята. Попробуйте другую");


        User user = new User(login, "Не в сети",
                passwordEncoder.encode(password),
                email, LocalDate.now());

        Set<Role> roles = new HashSet<>();


        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Роль USER не найдена"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "DEV":
                        Role devRole = roleRepository
                                .findByName(ERole.ROLE_DEV)
                                .orElseThrow(() -> new RoleNotFoundException("Роль DEV не найдена"));
                        roles.add(devRole);
                        break;
                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Роль USER не найдена"));
                        roles.add(userRole);
                }
            });
        }

        Wallet wallet = new Wallet(0.0);
        walletRepository.save(wallet);

        user.setRoles(roles);
        user.setWallet(wallet);
        userRepository.save(user);
        return user;
    }

    public void loginAsUser(String login) throws SQLException {
        userRepository.loginAsUser(login);
    }


}
