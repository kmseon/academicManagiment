package com.example.academicManagement.entity;

import com.example.academicManagement.constant.Gender;
import com.example.academicManagement.dto.ExamDto;
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
public class Student {
    @Id
    @Column(name = "student_no" , length = 6, nullable = false)
    private String studentNo;

    @Column(length = 10)
    private String name;

    @Column(length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 20)
    private String address;

}
