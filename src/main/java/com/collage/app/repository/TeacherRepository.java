package com.collage.app.repository;

import com.collage.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    List<Teacher> findByActiveTrue();
}
