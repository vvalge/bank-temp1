package com.bank.authorization.service;

import com.bank.authorization.dto.JwtAuthenticationResponse;
import com.bank.authorization.dto.SignInRequestDto;
import com.bank.authorization.dto.SignUpRequestDto;
import com.bank.authorization.exception.RegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;


@Service
@RequiredArgsConstructor
@Setter
@Log
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequestDto request) throws RegistrationException {

        var user = User.builder()
                .username(String.valueOf(request.getProfileId()))
                .password(passwordEncoder.encode(request.getPassword()))
                .roles("USER")
                .build();
        try {
            userService.addNewUser(user);
        } catch (RegistrationException e) {
            return new JwtAuthenticationResponse("", HttpStatus.UNAUTHORIZED);
        }

        var jwt = jwtService.generateToken(user);
        log.info("Create and send JwtAuthenticationResponce");
        return new JwtAuthenticationResponse(jwt, HttpStatus.OK);

    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getProfileId(),
                request.getPassword()
        ));

        try {
            var user = userService
                    .loadUserByUsername(String.valueOf(request.getProfileId()));
            log.log(Level.INFO, "Retrieved user information from database");

            var jwt = jwtService.generateToken(user);
            log.log(Level.INFO, "Generating an access token");
            return new JwtAuthenticationResponse(jwt, HttpStatus.OK);

        } catch (UsernameNotFoundException e) {
            return new JwtAuthenticationResponse("", HttpStatus.UNAUTHORIZED);
        }
    }
}
