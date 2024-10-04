package com.dgpad.project.controller;

import com.dgpad.project.DTO.response.request.UserRegistrationDto;
import com.dgpad.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";  // View: templates/register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto,
                               @RequestParam String role) {
        userService.registerUser(registrationDto, role);
        return "redirect:/auth/login";  // Redirect to login after registration
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // View: templates/login.html
    }


}