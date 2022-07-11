package com.collage.app.resource;

import com.collage.app.payload.TeacherCreatePayload;
import com.collage.app.payload.TeacherUpdatePayload;
import com.collage.app.service.CollageAdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherResource {

    private CollageAdministrationService collageAdministrationService;

    @GetMapping("teacher")
    public ResponseEntity<?> getAllTeacher() {
        return ResponseEntity.ok(collageAdministrationService.getAllTeacher());
    }

    @PostMapping()
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherCreatePayload payload) {
        return ResponseEntity.ok(collageAdministrationService.createTeacher(payload));
    }

    @PutMapping()
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody TeacherUpdatePayload payload) {
        return ResponseEntity.ok(collageAdministrationService.updateTeacher(payload));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        return ResponseEntity.ok(collageAdministrationService.deleteTeacher(id));
    }
}
