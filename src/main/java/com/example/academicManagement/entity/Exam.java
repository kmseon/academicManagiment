package com.example.academicManagement.entity;

import com.example.academicManagement.constant.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Exam {
    @Id
    @Column(name = "exam_no" , length = 6, nullable = false)
    private String examNo;

    @Column(length = 10)
    private int kor;

    @Column(length = 15)
    private int math;

    @Column(length = 4)
    private int eng;

    @Column(length = 20)
    private int hist;

}
