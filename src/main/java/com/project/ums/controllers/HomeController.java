package com.project.ums.controllers;

import com.project.ums.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "index2";
    }

//    @GetMapping("/home")
//    public String home() {
//        return "pages/charts/flot";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "/admin";
//    }
//
//    @GetMapping("/student")
//    public String user() {
//        return "/student";
//    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
