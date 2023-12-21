package com.bank.authorization.controller;

import com.bank.authorization.dto.JwtAuthenticationResponse;
import com.bank.authorization.dto.SignInRequestDto;
import com.bank.authorization.dto.SignUpRequestDto;
import com.bank.authorization.exception.RegistrationException;
import com.bank.authorization.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Level;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
@Log
@OpenAPIDefinition(info = @Info(
        title = "Authorization controller",
        version = "1.0.0",
        description = "контроллер для выполнения аутентификации и авторизации"))
public class AuthorisationController {

    private final AuthenticationService authenticationService;


    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequestDto request) throws RegistrationException {
        log.log(Level.INFO, "Registration request received");
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequestDto request) {
        log.log(Level.INFO, "Authorization request received");
        return authenticationService.signIn(request);
    }

}



