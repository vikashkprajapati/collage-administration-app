package com.collage.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class RegisterPayload {
    @NotBlank(message = "First Name is required!")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Password is required!")
    private String password;
}
