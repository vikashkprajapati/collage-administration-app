package com.collage.resource;

import com.collage.payload.LoginPayload;
import com.collage.payload.RegisterPayload;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthResource {
    private final com.collage.security.AuthService authService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody RegisterPayload payload,
                                    BindingResult result) {
        return authService.signUp(payload, result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload payload) {
        return authService.login(payload);
    }

    @GetMapping("check-email-exist/{email}")
    public ResponseEntity<?> checkEmailExist(@PathVariable("email") String email) {
        return authService.checkEmailExist(email);
    }

}
