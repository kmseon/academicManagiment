package com.example.academicManagement.dto;

import com.example.academicManagement.entity.Exam;
import com.example.academicManagement.entity.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {

    @NotBlank(message = "학번이 입력되지 않았습니다.")
    private String examNo;
    @Range(min = 0, max = 100, message = "성적은 0점에서 100점까지 입력 가능합니다.")
    private int kor;
    @Range(min = 0, max = 100, message = "성적은 0점에서 100점까지 입력 가능합니다.")
    private int math;
    @Range(min = 0, max = 100, message = "성적은 0점에서 100점까지 입력 가능합니다.")
    private int eng;
    @Range(min = 0, max = 100, message = "성적은 0점에서 100점까지 입력 가능합니다.")
    private int hist;

    public Exam fromExamDto(ExamDto examDto){
        Exam exam = new Exam();
        exam.setExamNo(examDto.getExamNo());
        exam.setKor(examDto.getKor());
        exam.setMath(examDto.getMath());
        exam.setEng(examDto.getEng());
        exam.setHist(examDto.getHist());

        return exam;
    }

    public static ExamDto toStudentDto(Exam exam){
        return new ExamDto(
                exam.getExamNo(),
                exam.getKor(),
                exam.getMath(),
                exam.getEng(),
                exam.getHist()
        );
    }

}
