package com.collage.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface ErrorCollector {

    Map<String, String> getErrorResponses(BindingResult result);

    ResponseEntity<?> getErrorResponsesEntity(BindingResult result);
}
