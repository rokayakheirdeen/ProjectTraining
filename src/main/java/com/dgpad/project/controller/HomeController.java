package com.dgpad.project.controller;

import ch.qos.logback.core.model.Model;
import com.dgpad.project.model.Feedback;
import com.dgpad.project.model.Order;
import com.dgpad.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Simply return the homepage view without adding the image URL to the model
        return "home";  // This refers to the Thymeleaf template named "home.html"
    }

    @GetMapping("/about")
    public String about() {

        return "about"; // This refers to about.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Refers to login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // Refers to register.html
    }



}