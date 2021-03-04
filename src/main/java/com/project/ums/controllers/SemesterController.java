package com.project.ums.controllers;


import com.project.ums.models.Semester;
import com.project.ums.models.Subject;
import com.project.ums.services.SemesterService;
import com.project.ums.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/semester")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/all")
    public String showAllSemester(Model model){
        model.addAttribute("semesters", semesterService.findAll());
        return "semester/all";
    }

    @GetMapping("/add")
    public String addSemester(Model model){
        Semester semester = new Semester();
        model.addAttribute("semester", semester);
        return "semester/add";
    }

    @PostMapping("/create")
    public String createSemester(@Valid Semester semester, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("semester", semester);
            return "semester/add";
        }

        semesterService.save(semester);
        model.addAttribute("semesters", semesterService.findAll());
        return "semester/all";
    }
}
