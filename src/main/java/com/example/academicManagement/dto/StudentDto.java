package com.example.academicManagement.dto;

import com.example.academicManagement.constant.Gender;
import com.example.academicManagement.entity.Exam;
import com.example.academicManagement.entity.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    @Column(name = "student_no")
    private String studentNo;
    private String name;
    private String phone;
    private Gender gender;
    private String address;
    private ExamDto examDto;
//    private List<ExamDto> examDto = new ArrayList<>();
    private int total;
    private double avg;
    private int rank;

    public StudentDto(String studentNo, String name, String phone, Gender gender, String address) {
        this.studentNo = studentNo;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }


    public static StudentDto fromStudentEntity(Student student) {
        return new StudentDto(
                student.getStudentNo(),
                student.getName(),
                student.getPhone(),
                student.getGender(),
                student.getAddress()
        );
    }
}

