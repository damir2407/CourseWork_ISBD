package com.oasis.oasisapp.controller;

import com.oasis.oasisapp.config.jwt.JwtUtils;
import com.oasis.oasisapp.dto.SignInRequest;
import com.oasis.oasisapp.dto.MessageResponse;
import com.oasis.oasisapp.dto.SignUpRequest;
import com.oasis.oasisapp.dto.UserDataResponse;
import com.oasis.oasisapp.exception.RoleNotFoundException;
import com.oasis.oasisapp.exception.UserAlreadyExistException;
import com.oasis.oasisapp.security.UserDetailsImpl;
import com.oasis.oasisapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/oasis/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private AuthService authService;


    @PostMapping("/sign-in")
    public ResponseEntity<?> authUser(@RequestBody SignInRequest signInRequest) throws BadCredentialsException, SQLException {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getLogin(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        authService.loginAsUser(signInRequest.getLogin());

        return ResponseEntity.ok(new UserDataResponse(jwt, userDetails.getLogin(), roles));


    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws UserAlreadyExistException, RoleNotFoundException {


        authService.saveUser(signUpRequest.getLogin(), signUpRequest.getPassword(),
                signUpRequest.getEmail(), signUpRequest.getRoles());

        return ResponseEntity.ok(new MessageResponse("Пользователь успешно зарегистрирован!"));


    }


}
