package com.project.ums.controllers;

import com.project.ums.authentication.MyUserDetails;
import com.project.ums.controllers.dto.UserDTO;
import com.project.ums.models.Marks;
import com.project.ums.models.Subject;
import com.project.ums.models.User;
import com.project.ums.services.MarksService;
import com.project.ums.services.SemesterService;
import com.project.ums.services.SubjectService;
import com.project.ums.services.UserService;
import com.project.ums.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.error.Mark;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @Autowired
    private UserService userService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/add")
    public String addMarks(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        Marks mark = new Marks();
        List<Subject> subjectList = Utils.convertSetToList(userDetails.getSubjects());

        var subjects =  Utils.convertSetToList(userDetails.getSubjects()).stream().map((subject) -> subject.getId()).collect(Collectors.toList());
        List<UserDTO> students = userService.EnrolledStudents(subjects);

        model.addAttribute("marks", mark);
        model.addAttribute("semesters", semesterService.findAll());
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjectList);

        return "lecturer/enterMarks";
    }

    @PostMapping("/create")
    public String createMarks(@AuthenticationPrincipal MyUserDetails userDetails, @Valid Marks marks, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Marks mark = new Marks();
            List<Subject> subjectList = Utils.convertSetToList(userDetails.getSubjects());

            var subjects =  Utils.convertSetToList(userDetails.getSubjects()).stream().map((subject) -> subject.getId()).collect(Collectors.toList());
            List<UserDTO> students = userService.EnrolledStudents(subjects);

            model.addAttribute("marks", mark);
            model.addAttribute("semesters", semesterService.findAll());
            model.addAttribute("students", students);
            model.addAttribute("subjects", subjectList);

            return "lecturer/enterMarks";
        }

        marksService.add(marks);

        var subjects =  Utils.convertSetToList(userDetails.getSubjects()).stream().map((subject) -> subject.getId()).collect(Collectors.toList());
        List<UserDTO> students = userService.EnrolledStudents(subjects);

        model.addAttribute("users", students);
        return "lecturer/students";
    }

    @GetMapping("/report/{id}")
    public String studentReport(@PathVariable("id") Long id, Model model) {
        var allMarks = marksService.findMarksByUser(id);
        Map<Integer, List<Marks>> groupByMarkMap =
                allMarks.stream().collect(Collectors.groupingBy(Marks::getSemesterId));

        model.addAttribute("report", groupByMarkMap);
        model.addAttribute("gpa", 3.25);
        return "student/report";
    }

    @GetMapping("/report")
    public String studentReport(@AuthenticationPrincipal MyUserDetails userDetails,  Model model) {
        var allMarks = marksService.findMarksByUser(userDetails.getId());
        Map<Integer, List<Marks>> groupByMarkMap =
                allMarks.stream().collect(Collectors.groupingBy(Marks::getSemesterId));

        model.addAttribute("report", groupByMarkMap);
        return "student/report";
    }

}
