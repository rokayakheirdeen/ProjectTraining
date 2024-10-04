package com.dgpad.project.controller;

import com.dgpad.project.model.Feedback;
import com.dgpad.project.model.User;
import com.dgpad.project.repository.UserRepository;
import com.dgpad.project.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserRepository userRepository;

    // Show feedback form
    @GetMapping("/new")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback/new";
    }

    // Handle feedback submission
    @PostMapping("/submit")
    public String submitFeedback(@ModelAttribute Feedback feedback, @RequestParam("userId") Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            feedback.setUser(user);
            feedbackService.saveFeedback(feedback); // Use the service to save feedback
        }
        return "redirect:/feedback/success";
    }

    // Show success page after submission
    @GetMapping("/success")
    public String feedbackSuccess() {
        return "feedback/success";
    }

    // Optional: Admin can view all feedback
    @GetMapping("/list")
    public String listFeedback(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "feedback/list"; // Create this Thymeleaf template to show feedback
    }
}
