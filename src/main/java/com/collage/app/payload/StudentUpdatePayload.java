package com.collage.app.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentUpdatePayload {

    private Long id;
    private String firstName;
    private String lastName;
    private Long age;
    private String mobileNumber;
    private String altMobileNumber;
    private String usn;


}
