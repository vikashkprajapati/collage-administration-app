package com.collage.app.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherUpdatePayload {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
