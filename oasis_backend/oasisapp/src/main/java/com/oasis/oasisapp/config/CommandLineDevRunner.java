package com.oasis.oasisapp.config;


import com.oasis.oasisapp.model.Dev;
import com.oasis.oasisapp.service.AuthService;
import com.oasis.oasisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommandLineDevRunner implements CommandLineRunner {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<Dev> devAccounts = new ArrayList<>();
        devAccounts.add(new Dev("alex1", "123456", "alex1@gmail.com"));
        devAccounts.add(new Dev("ssss2407", "123456", "ssss2407@gmail.com"));
        devAccounts.add(new Dev("zQx", "123456", "zQx@gmail.com"));
        devAccounts.add(new Dev("kokls", "123456", "kokls@gmail.com"));
        devAccounts.add(new Dev("speed", "123456", "speed@gmail.com"));
        devAccounts.add(new Dev("andrews", "123456", "andrews@gmail.com"));
        devAccounts.add(new Dev("uglyaf", "123456", "uglyaf@gmail.com"));

        for (Dev dev : devAccounts) {
            if (!userService.checkUserOnExist(dev.getLogin()))
                authService.saveUser(dev.getLogin(), dev.getPassword(), dev.getEmail(), dev.getRole());
        }

    }
}
