package com.collage.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class LoginPayload {
    @NotBlank(message = "Username is required!")
    @NotNull(message = "Username is required!")
    private String username;

    @NotBlank(message = "Password is required!")
    @NotNull(message = "Password is required!")
    private String password;
}
