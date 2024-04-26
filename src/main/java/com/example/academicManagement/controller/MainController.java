package com.example.academicManagement.controller;

import com.example.academicManagement.dto.ExamDto;
import com.example.academicManagement.dto.StudentDto;
import com.example.academicManagement.entity.Exam;
import com.example.academicManagement.entity.Student;
import com.example.academicManagement.service.ManageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/academicManagementView")
public class MainController {
    private final ManageService manageService;

    public MainController(ManageService manageService) {
        this.manageService = manageService;
    }

    @GetMapping("")
    public String mainView(){
        return "/academicManagementView/index";
    }
    @GetMapping("/list")
    public String listView(Model model){
        List<StudentDto> studentDtoList = manageService.list_all();
        log.info(studentDtoList.toString());
        model.addAttribute("studentDtoList", studentDtoList);
        return "/academicManagementView/studentList";
    }

    @GetMapping("/insert")
    public String insertView(Model model){
        model.addAttribute("examDto", new ExamDto());
        return "/academicManagementView/gradeInsert";
    }

    @PostMapping("/insert")
    public String insertForm(@Valid @ModelAttribute("examDto") ExamDto examDto,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("Validation Error");
            return "/academicManagementView/gradeInsert";
        }
        manageService.insecrt_grade(examDto);
        return "redirect:/";
    }

    @GetMapping("/grade")
    public String gradeList(Model model){
        List<StudentDto> studentDtoList = manageService.gradList_all();
        model.addAttribute("studentDtoList", studentDtoList);

        //exam에 있는 각 과목 성적 총합 만들어 가져오기
        Exam examSum = manageService.createExam();
        model.addAttribute("examSum", examSum);

        //exam에 있는 각 과목 성적 평균 만들어 가져오기
        Exam examAvg = manageService.createExamAvg();
        model.addAttribute("examAvg", examAvg);

        //studentList > 합계 , 평균 , 석차 추가해서 가져오기  ^^^^
//        List<StudentDto> newStudentDto = manageService.setExamInMember(studentDtoList);
        return "/academicManagementView/gradeList";
    }
}
