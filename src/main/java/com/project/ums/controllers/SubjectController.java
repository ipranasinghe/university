package com.project.ums.controllers;

import com.project.ums.authentication.MyUserDetails;
import com.project.ums.models.Subject;
import com.project.ums.repository.UserRepository;
import com.project.ums.services.SubjectService;
import com.project.ums.services.UserService;
import com.project.ums.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String showAllSubjects(Model model){
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/all";
    }

    @GetMapping("/my")
    public String showAllSubjects(@AuthenticationPrincipal MyUserDetails userDetails,Model model){
        List<Subject> list = Utils.convertSetToList(userDetails.getSubjects());
        var user = userService.findById(userDetails.getId());
        model.addAttribute("subjects", list);
        return "lecturer/subjects";
    }

    @GetMapping("/signup")
    public String addSubject(Model model){
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/add";
    }

    @PostMapping("add")
    public String createSubject(@Valid Subject subject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subject", subject);
            return "subject/add";
        }

        subjectService.save(subject);
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/all";
    }

    @GetMapping("edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        Subject subject = subjectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject Id:" + id));
        model.addAttribute("subject", subject);
        return "subject/update";
    }

    @PostMapping("update/{id}")
    public String updateSubject(@PathVariable("id") int id, @Valid Subject subject, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            subject.setId(id);
            return "subject/update";
        }
        subjectService.save(subject);
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/all";
    }

    @GetMapping("delete/{id}")
    public String deleteSubject(@PathVariable("id") int id, Model model) {
        Subject student = subjectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        subjectService.delete(student);
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/all";
    }

}
