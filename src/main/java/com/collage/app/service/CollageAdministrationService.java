package com.collage.app.service;


import com.collage.app.payload.StudentCreatePayload;
import com.collage.app.payload.StudentUpdatePayload;
import com.collage.app.payload.TeacherCreatePayload;
import com.collage.app.payload.TeacherUpdatePayload;
import com.collage.app.response.StudentResponse;
import com.collage.app.response.TeacherResponse;
import com.collage.response.APIResponse;
import io.swagger.v3.oas.models.responses.ApiResponse;

public interface CollageAdministrationService {

    StudentResponse getAllStudent();

    APIResponse createStudent(StudentCreatePayload payload);

    APIResponse updateStudent(StudentUpdatePayload payload);

    APIResponse deleteStudent(Long id);

     TeacherResponse getAllTeacher();

    APIResponse createTeacher(TeacherCreatePayload payload);

    APIResponse updateTeacher(TeacherUpdatePayload payload);

    APIResponse deleteTeacher(Long id);
}
