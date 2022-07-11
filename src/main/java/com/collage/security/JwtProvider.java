package com.technoboost.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    String generateToken(Authentication authentication);

    String getUsernameFromJWT(String token);

    boolean validateToken(String jwt);
}
