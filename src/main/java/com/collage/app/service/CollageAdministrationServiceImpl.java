package com.collage.app.service;

import com.collage.app.model.Student;
import com.collage.app.model.Teacher;
import com.collage.app.payload.StudentCreatePayload;
import com.collage.app.payload.StudentUpdatePayload;
import com.collage.app.payload.TeacherCreatePayload;
import com.collage.app.payload.TeacherUpdatePayload;
import com.collage.app.repository.StudentRepository;
import com.collage.app.repository.TeacherRepository;
import com.collage.app.response.StudentDTO;
import com.collage.app.response.StudentResponse;
import com.collage.app.response.TeacherDTO;
import com.collage.app.response.TeacherResponse;
import com.collage.exception.GeneralException;
import com.collage.response.APIResponse;
import com.collage.response.UtilMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CollageAdministrationServiceImpl implements CollageAdministrationService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    @Override
    public StudentResponse getAllStudent() {
        var res = new StudentResponse();
        List<Student> studentList = studentRepository.findByActiveTrue();
        var student = studentList
                .stream()
                .map(s -> StudentDTO.builder()
                        .age(s.getAge())
                        .firstName(s.getFirstName())
                        .lastName(s.getLastName())
                        .mobileNumber(s.getMobileNumber())
                        .altMobileNumber(s.getAltMobileNumber())
                        .usn(s.getUsn())
                        .build())
                .toList();
        res.setStudent(student);
        return res;
    }

    @Override
    public APIResponse createStudent(StudentCreatePayload payload) {
        var student = new Student();
        student.setAge(payload.getAge());
        student.setFirstName(payload.getFirstName());
        student.setLastName(payload.getLastName());
        student.setMobileNumber(payload.getMobileNumber());
        student.setAltMobileNumber(payload.getAltMobileNumber());
        student.setUsn(payload.getUsn());
        studentRepository.saveAndFlush(student);
        return APIResponse.builder()
                .id(student.getId())
                .message("Student is created successfully")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public APIResponse updateStudent(StudentUpdatePayload payload) {
        var student = studentRepository.findByIdAndActiveTrue(payload.getId())
                .orElseThrow(() -> new GeneralException("Student not found with id:" + payload.getId()));
        student.setMobileNumber(payload.getMobileNumber());
        student.setAltMobileNumber(payload.getAltMobileNumber());
        student.setUsn(payload.getUsn());
        student.setFirstName(payload.getFirstName());
        student.setLastName(payload.getLastName());
        return APIResponse.builder()
                .id(student.getId())
                .message("Student is Updated successfully")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public APIResponse deleteStudent(Long id) {
        var student = studentRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new GeneralException("Student not found with id:" + id));
        student.setActive(false);
        return APIResponse.builder()
                .id(student.getId())
                .message("Student deleted successfully")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public TeacherResponse getAllTeacher() {
        var res = new TeacherResponse();
        List<Teacher> teacherList = teacherRepository.findByActiveTrue();
        var teacher = teacherList.stream()
                .map(t -> TeacherDTO.builder()
                        .firstName(t.getFirstName())
                        .lastName(t.getLastName())
                        .mobileNumber(t.getMobileNumber())
                        .build())
                .toList();
        res.setTeacher(teacher);
        return res;
    }

    @Override
    public APIResponse createTeacher(TeacherCreatePayload payload) {
        return null;
    }

    @Override
    public APIResponse updateTeacher(TeacherUpdatePayload payload) {
        return null;
    }

    @Override
    public APIResponse deleteTeacher(Long id) {
        return null;
    }
}
