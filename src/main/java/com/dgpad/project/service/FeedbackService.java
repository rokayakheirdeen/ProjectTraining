package com.dgpad.project.service;

import com.dgpad.project.model.Feedback;
import com.dgpad.project.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save new feedback
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Get all feedback (for admin purposes)
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Optional: Get feedback by ID
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    // Optional: Delete feedback by ID (for admin)
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
