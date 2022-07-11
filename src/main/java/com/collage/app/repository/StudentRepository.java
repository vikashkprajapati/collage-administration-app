package com.collage.app.repository;

import com.collage.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
    List<Student> findByActiveTrue();


    Optional<Student> findByIdAndActiveTrue(Long id);
}
