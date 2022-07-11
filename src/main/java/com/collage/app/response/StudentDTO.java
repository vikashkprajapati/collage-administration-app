package com.collage.app.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentDTO {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Long age;
    private String altMobileNumber;
    private String usn;

}
