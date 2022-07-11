package com.collage.app.aduit.security;


import com.collage.exception.GeneralException;
import com.collage.model.User;
import com.collage.payload.LoginPayload;
import com.collage.payload.RegisterPayload;
import com.collage.repository.UserRepository;
import com.collage.response.APIResponse;
import com.collage.response.AuthenticationResponse;
import com.collage.response.UtilMethod;
import com.collage.security.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ErrorCollector;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServicesImpl implements AuthService {

    private final ErrorController errorController;
    private final UserPrincipal userPrincipal;
    private final RolesRepository rolesRepository;

    private final UserRepository userRepository;
    private final ErrorCollector errorCollector;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
//    private final JwtProvider jwtProvider;

    public static String getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        return principal.getName();
    }




    @Override
    public ResponseEntity<?> signUp(@Valid RegisterPayload payload, BindingResult result) {
        ResponseEntity<?> responseEntity;
        if (result.hasErrors()) {
            //responseEntity = errorCollector.getErrorResponsesEntity(result);
        } else {
            User user = createUser(payload);
            LoginPayload loginPayload = new LoginPayload();
            loginPayload.setPassword(payload.getPassword());
            loginPayload.setUsername(user.getEmail());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                    .body(login(loginPayload));
        }

        return null; // responseEntity;
    }
    private User createUser(RegisterPayload payload) {
        User user = new User();
        user.setUsername(payload.getEmail());
        user.setEmail(payload.getEmail());
        user.setPassword(encodePassword(payload.getPassword()));
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        Role role = rolesRepository.findByRoleAndActiveTrue("")
                .orElseThrow(() -> new GeneralException("Check role : " + "ADMIN"));
        user.setRoles(Collections.singleton(role));
        return userRepository.save(user);
    }
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    @Override
    public ResponseEntity<?> login(LoginPayload payload) {
            log.error("Payload : " + payload.getUsername());
            Authentication authenticate;
            try {
                authenticate = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(),
                                payload.getPassword()));
            } catch (AuthenticationException e) {
                log.error("Login Error : {}", e.getMessage());
                return new ResponseEntity<>(APIResponse.builder()
                        .id(0L)
                        .message(e.getMessage())
                        .path(UtilMethod.getPath())
                        .status(HttpStatus.NOT_FOUND.name())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .success(false)
                        .timestamp(LocalDateTime.now().toString())
                        .build(), HttpStatus.OK);
                //throw new GeneralException("" + e.getMessage());
            }

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String authenticationToken = jwtProvider.generateToken(authenticate);
            log.info("Token : " + authenticationToken);
            UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AuthenticationResponse response = new AuthenticationResponse(authenticationToken, payload.getUsername());
            response.setFirstName(principal.getUser().getFirstName());
            response.setLastName(principal.getUser().getLastName());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @Override
    public ResponseEntity<?> checkEmailExist(String email) {
        boolean exist = userRepository.existsByEmailAndEnabledTrue(email);
        APIResponse res;
        if (exist) {
            res = APIResponse
                    .builder()
                    .id(0L)
                    .message("Email already in use : " + email)
                    .path(UtilMethod.getPath())
                    .status(HttpStatus.IM_USED.name())
                    .statusCode(HttpStatus.IM_USED.value())
                    .success(false)
                    .timestamp(LocalDateTime.now().toString())
                    .build();
        } else {
            res = APIResponse
                    .builder()
                    .id(0L)
                    .message(email + " Email is available")
                    .path(UtilMethod.getPath())
                    .status(HttpStatus.NOT_FOUND.name())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .success(true)
                    .timestamp(LocalDateTime.now().toString())
                    .build();
        }
        return ResponseEntity.ok(res);
    }
}


}

