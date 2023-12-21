package com.bank.authorization.service;

import com.bank.authorization.dto.JwtAuthenticationResponse;
import com.bank.authorization.dto.SignInRequestDto;
import com.bank.authorization.dto.SignUpRequestDto;
import com.bank.authorization.exception.RegistrationException;

public interface AuthenticationService {
    public JwtAuthenticationResponse signUp(SignUpRequestDto request) throws RegistrationException;

    public JwtAuthenticationResponse signIn(SignInRequestDto request);
}
