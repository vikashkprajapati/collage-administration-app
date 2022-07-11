package com.collage.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorCollectorImpl implements ErrorCollector {

    @Override
    public Map<String, String> getErrorResponses(BindingResult result) {

        return result
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,
                        FieldError::getDefaultMessage, (a, b) -> b));
    }

    @Override
    public ResponseEntity<?> getErrorResponsesEntity(BindingResult result) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getErrorResponses(result));
    }
}
