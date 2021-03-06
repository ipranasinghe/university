package com.project.ums.controllers;

import com.project.ums.authentication.MyUserDetails;
import com.project.ums.models.Inquirie;
import com.project.ums.models.Subject;
import com.project.ums.services.MailService;
import com.project.ums.services.SubjectService;
import com.project.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addMail(Model model){
        Inquirie mail = new Inquirie();
        model.addAttribute("mail", mail);
        return "mails/createMail";
    }

    @PostMapping("/create")
    public String createMail(@AuthenticationPrincipal MyUserDetails userDetails, @Valid Inquirie mail, BindingResult result, Model model) {
        mail.setUser(userService.findById(userDetails.getId()).get());
        mailService.addMail(mail);
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/all";
    }

    @GetMapping("/all")
    public String allMails(@AuthenticationPrincipal MyUserDetails userDetails, @Valid Inquirie mail, BindingResult result, Model model) {

        var mails = mailService.allMails();
        var user = userService.getUserDTO(userDetails.getId());

        if (user.getType() == 1){
            model.addAttribute("mails", mails);
        }
        else{
            model.addAttribute("mails", user.getMails());
        }

        return "mails/mail";
    }

    @GetMapping("/solve/{id}")
    public void answer(@PathVariable("id") int id, Model model){
        mailService.solveMail(id);
    }
}
