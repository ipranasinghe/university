package com.project.ums.controllers;

import com.project.ums.authentication.MyUserDetails;
import com.project.ums.configurations.WebSecurityConfig;
import com.project.ums.controllers.dto.UserDTO;
import com.project.ums.models.Subject;
import com.project.ums.models.User;
import com.project.ums.services.UserService;
import com.project.ums.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @GetMapping("/all")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }

    @GetMapping("/create")
    public String addUser(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "user/add";
    }

    @PostMapping("/add")
    public String createUser(@Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "user/add";
        }

        userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userService.save(userDTO);
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }

    @GetMapping("view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));

        UserDTO userDTO  = userService.convert(user);
        model.addAttribute("user", userDTO);
        return "user/view";
    }

    @GetMapping("edit/{id}")
    public String update(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable("id") Long id, Model model) {
        logger.debug("Profile related to id: "+ id+" is edited by admin :" + userDetails.getUsername());
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        UserDTO userDTO  = userService.convert(user);
        model.addAttribute("user", userDTO);
        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser( @Valid UserDTO user, BindingResult result, Model model) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }

    @GetMapping("/students")
    public String showAllUsers(@AuthenticationPrincipal MyUserDetails userDetails, Model model){

        var subjects =  Utils.convertSetToList(userDetails.getSubjects()).stream().map((subject) -> subject.getId()).collect(Collectors.toList());
        List<UserDTO> students = userService.EnrolledStudents(subjects);

        model.addAttribute("users", students);
        return "lecturer/students";
    }
}
