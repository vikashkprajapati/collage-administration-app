package com.collage.app.resource;

import com.collage.app.payload.StudentCreatePayload;
import com.collage.app.payload.StudentUpdatePayload;
import com.collage.app.service.CollageAdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentResource {

    private final CollageAdministrationService collageAdministrationService;

    @GetMapping("student")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(collageAdministrationService.getAllStudent());
    }

    @PostMapping()
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentCreatePayload payload){
        return ResponseEntity.ok(collageAdministrationService.createStudent(payload));
    }
    @PutMapping()
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentUpdatePayload payload){
        return ResponseEntity.ok(collageAdministrationService.updateStudent(payload));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable ("id")Long id){
        return ResponseEntity.ok(collageAdministrationService.deleteStudent(id));
    }
}
