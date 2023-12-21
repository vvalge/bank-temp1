package com.bank.authorization.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String extractProfileId(String token);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
