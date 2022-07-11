package com.collage.security;


import com.collage.payload.LoginPayload;
import com.collage.payload.RegisterPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface AuthService {
    ResponseEntity<?> signUp(@Valid RegisterPayload payload, BindingResult result);

    ResponseEntity<?> login(LoginPayload payload);

    ResponseEntity<?> checkEmailExist(String email);
}
