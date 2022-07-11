package com.collage.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> unauthorizedAccess() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Access Denied");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(com.collage.exception.GeneralException.class)
    @ResponseBody
    public ResponseEntity<?> generalException(com.collage.exception.GeneralException e) {
        return ResponseEntity.ok(com.collage.response.APIResponse.builder()
                .message(e.getMessage())
                .path(com.collage.response.UtilMethod.getPath())
                .status(HttpStatus.BAD_REQUEST.name())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .id(0L)
                .success(false)
                .timestamp(com.collage.response.UtilMethod.getCurrentDateTimeInString())
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.ok(com.collage.response.APIResponse.builder()
                .message(ex.getMessage())
                .path(com.collage.response.UtilMethod.getPath())
                .status(HttpStatus.BAD_REQUEST.name())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .id(0L)
                .success(false)
                .timestamp(com.collage.response.UtilMethod.getCurrentDateTimeInString())
                .build());
    }
}