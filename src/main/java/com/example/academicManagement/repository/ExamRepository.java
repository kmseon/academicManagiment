package com.example.academicManagement.repository;

import com.example.academicManagement.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, String> {
    @Query(value = "select sum(kor) from exam", nativeQuery = true)
    int korNumQuery();
    @Query(value = "select  sum(math) from exam", nativeQuery = true)
    int mathNumQuery();
    @Query(value = "select  sum(eng) from exam", nativeQuery = true)
    int engNumQuery();
    @Query(value = "select sum(hist) from exam", nativeQuery = true)
    int histNumQuery();
}
