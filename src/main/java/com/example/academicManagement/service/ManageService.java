package com.example.academicManagement.service;

import com.example.academicManagement.dto.ExamDto;
import com.example.academicManagement.dto.StudentDto;
import com.example.academicManagement.entity.Exam;
import com.example.academicManagement.entity.Student;
import com.example.academicManagement.repository.ExamRepository;
import com.example.academicManagement.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ManageService {
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public ManageService(StudentRepository studentRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public List<StudentDto> list_all() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList = studentRepository.findAll()
                .stream()
                .map(x -> StudentDto.fromStudentEntity(x))
                .toList();
        return studentDtoList;
    }

    public void insecrt_grade(ExamDto examDto) {
        Exam exam = examDto.fromExamDto(examDto);
        examRepository.save(exam);
    }


    public List<StudentDto> gradList_all() {
        List<Student> studentList = studentRepository.findAll();

        List<StudentDto> studentDtoList = new ArrayList<>();
        int total = 0;
        double avg = 0;
        int rank = 0;
        for (Student student : studentList) {
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentNo(student.getStudentNo());
            studentDto.setName(student.getName());
            Exam exam = examRepository.findById(student.getStudentNo()).orElse(null);
            ExamDto examDto = new ExamDto();
                if (exam == null) {
                examDto = null;
                 } else {
                    examDto.setExamNo(exam.getExamNo());
                    examDto.setKor(exam.getKor());
                    examDto.setMath(exam.getMath());
                    examDto.setEng(exam.getEng());
                    examDto.setHist(exam.getHist());
                    studentDto.setExamDto(examDto);
                }
            if (studentDto.getExamDto() != null) {
                total = studentDto.getExamDto().getKor() +
                        studentDto.getExamDto().getMath() +
                        studentDto.getExamDto().getEng() +
                        studentDto.getExamDto().getHist();

                avg = total / (double) 4;
                studentDto.setTotal(total);
                studentDto.setAvg(avg);
                studentDto.setRank(1);
            }else{
                studentDto.setTotal(0);
                studentDto.setAvg(0);
            }
            studentDtoList.add(studentDto);
//            for (int i=0; i<studentDtoList.size(); i++) {
//                if (total <= studentDtoList.get(i).getTotal()) {
//                    rank = studentDto.getRank() + 1;
//                } else{
//                    studentDto.setRank(1);
//                    break;
//                }
//            }
        }
        //0~DtoList 현재 길이 만큼
        for (int i=0; i<studentDtoList.size(); i++) {
            int firstTotal;
            firstTotal = studentDtoList.get(i).getTotal();
            log.info("1 : "+String.valueOf(firstTotal));
            for (int j = 0; j < studentDtoList.size(); j++) {
                if (firstTotal < studentDtoList.get(j).getTotal()) {
                    rank = studentDtoList.get(i).getRank() + 1;
                    studentDtoList.get(i).setRank(rank);
                } else {
                    break;
                }
                log.info("2 :"+String.valueOf(rank));
            }
        }
        log.info("3"+studentDtoList.toString());
        return studentDtoList;
    }


    public Exam createExam() {
        List<Exam> examList = examRepository.findAll();
        Exam exam = new Exam();
        for (int i=0; i <= examList.size()- 1; i++) {
            exam.setKor(examList.stream().mapToInt(Exam::getKor).sum());
            exam.setMath(examList.stream().mapToInt(Exam::getMath).sum());
            exam.setEng(examList.stream().mapToInt(Exam::getEng).sum());
            exam.setHist(examList.stream().mapToInt(Exam::getHist).sum());

        }
        log.info(exam.toString());
        return exam;
    }

    public Exam createExamAvg() {
        List<Exam> examList = examRepository.findAll();
        Exam exam = new Exam();
        for (int i=0; i <= examList.size()- 1; i++) {
            exam.setKor((int)examList.stream().mapToDouble(Exam::getKor).average().orElse(0));
            exam.setMath((int)examList.stream().mapToDouble(Exam::getMath).average().orElse(0));
            exam.setEng((int)examList.stream().mapToDouble(Exam::getEng).average().orElse(0));
            exam.setHist((int)examList.stream().mapToDouble(Exam::getHist).average().orElse(0));

        }
        log.info(exam.toString());
        return exam;
    }
}
