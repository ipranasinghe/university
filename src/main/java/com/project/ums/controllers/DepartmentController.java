package com.project.ums.controllers;

import com.project.ums.models.Department;
import com.project.ums.models.DepartmentCode;
import com.project.ums.models.Subject;
import com.project.ums.services.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public String showAllSubjects(Model model){
        model.addAttribute("departments", departmentService.findAll());
        return "department/all";
    }

    @GetMapping("/add")
    public String addDepartment(Model model){
        Department department = new Department();
        department.setDepartmentCode(DepartmentCode.CE);
        model.addAttribute("department", department);
        return "department/add";
    }

    @PostMapping("/create")
    public String createDepartment(@Valid Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("department", department);
            return "department/add";
        }

        departmentService.save(department);
        model.addAttribute("departments", departmentService.findAll());
        return "department/all";
    }
}
