package com.project.ums.controllers;

import com.project.ums.controllers.dto.DepartmentEnrollmentDTO;
import com.project.ums.controllers.dto.SubjectEnrollmentDTO;
import com.project.ums.controllers.dto.UserDTO;
import com.project.ums.models.Department;
import com.project.ums.services.DepartmentService;
import com.project.ums.services.SubjectService;
import com.project.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @GetMapping("/department/add")
    public String addDepartmentEnrollment(Model model){
        DepartmentEnrollmentDTO departmentEnrollmentDTO = new DepartmentEnrollmentDTO();
        model.addAttribute("enroll", departmentEnrollmentDTO);
        model.addAttribute("users", userService.findUsersExceptAdmins());
        model.addAttribute("departments", departmentService.findAll());
        return "enrollments/department";
    }

    @PostMapping("/department/create")
    public String createDepartmentEnrollment(@Valid DepartmentEnrollmentDTO departmentEnrollmentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            DepartmentEnrollmentDTO departmentEnrollment = new DepartmentEnrollmentDTO();
            model.addAttribute("enroll", departmentEnrollment);
            model.addAttribute("users", userService.findAll());
            model.addAttribute("departments", departmentService.findAll());
            return "enrollments/department";
        }
        departmentService.addDepartmentEnrollment(departmentEnrollmentDTO);
        model.addAttribute("departments", departmentService.findAll());
        return "department/all";
    }


    @GetMapping("/subjects/add")
    public String addSubjectEnrollment(Model model){
        SubjectEnrollmentDTO subjectEnrollmentDTO = new SubjectEnrollmentDTO();
        model.addAttribute("enroll", subjectEnrollmentDTO);
        model.addAttribute("users", userService.findUsersExceptAdmins());
        model.addAttribute("subjects", subjectService.findAll());
        return "enrollments/subject";
    }

    @PostMapping("/subjects/create")
    public String createSubjectEnrollment(@Valid SubjectEnrollmentDTO subjectEnrollmentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            SubjectEnrollmentDTO subjectEnrollment = new SubjectEnrollmentDTO();
            model.addAttribute("enroll", subjectEnrollment);
            model.addAttribute("users", userService.findUsersExceptAdmins());
            model.addAttribute("subjects", subjectService.findAll());
            return "enrollments/subject";
        }
        userService.addSubjectEnrollment(subjectEnrollmentDTO);
        model.addAttribute("departments", departmentService.findAll());
        return "department/all";
    }
}
