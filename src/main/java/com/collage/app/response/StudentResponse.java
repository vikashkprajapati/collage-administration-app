package com.collage.app.response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StudentResponse {

    List<StudentDTO> student = new ArrayList<>();


}
