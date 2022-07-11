package com.collage.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeacherDTO {

    private String firstName;
    private String lastName;
    private String mobileNumber;

}
