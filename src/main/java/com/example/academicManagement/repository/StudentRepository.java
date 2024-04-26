package com.example.academicManagement.repository;

import com.example.academicManagement.dto.StudentDto;
import com.example.academicManagement.entity.Exam;
import com.example.academicManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "select *, sum, sum(eng), sum(hist) from quiz order by rand() limit 1" , nativeQuery = true)
    List<StudentDto> studentTotalQuery();
}

