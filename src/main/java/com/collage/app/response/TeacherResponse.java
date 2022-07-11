package com.collage.app.response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TeacherResponse {

    List<TeacherDTO>  teacher = new ArrayList<>();
}
