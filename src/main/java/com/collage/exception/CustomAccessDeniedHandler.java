package com.collage.exception;

import com.collage.response.APIResponse;
import com.collage.response.UtilMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(APIResponse.builder()
                .timestamp(UtilMethod.getCurrentDateTimeInString())
                .id(0L)
                .path(UtilMethod.getPath())
                .message(e.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .status(HttpStatus.UNAUTHORIZED.name())
                .build()));
    }
}