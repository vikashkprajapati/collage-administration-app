package com.collage.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilMethod {


    private static final String DATE_FORMATTING_STRING = "dd-MMM-yyyy";
    private static final String DATE_FORMATTING_STRING_YY_MM_DD = "yyMMdd";


    public static String getPath() {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build().toUri();
        return location.getPath();
    }

    public static String getPath(Long id) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
        return location.getPath();
    }

    public static APIResponse apiResponse(
            Long id,
            int statusCode,
            String status,
            String message) {
        return APIResponse.builder()
                .statusCode(statusCode)
                .status(status)
                .id(id)
                .path(getPath())
                .message(message)
                .timestamp(Instant.now().toString())
                .build();
    }

    public static String getCurrentDateTimeInString() {
        return LocalDateTime.now().toString();

    }

    public static LocalDate convertStringToLocalDate(String localDate) {
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_FORMATTING_STRING));
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_FORMATTING_STRING));
    }

    public static String convertYYMMDDToLocalStringDate(String yyddmm) {
        return LocalDate.parse(yyddmm, DateTimeFormatter.ofPattern(DATE_FORMATTING_STRING_YY_MM_DD))
                .format(DateTimeFormatter.ofPattern(DATE_FORMATTING_STRING));
    }
}