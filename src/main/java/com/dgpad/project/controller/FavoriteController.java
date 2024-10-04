package com.dgpad.project.controller;

import com.dgpad.project.model.Favorite;
import com.dgpad.project.model.Product;
import com.dgpad.project.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Show favorites page
    @GetMapping
    public String viewFavorites(@RequestParam Long userId, Model model) {
        try {
            List<Favorite> favorites = favoriteService.getUserFavorites(userId);
            model.addAttribute("favorites", favorites);
            model.addAttribute("userId", userId);  // Add userId to the model
            return "favorites";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to retrieve favorites: " + e.getMessage());
            return "error";
        }
    }

    // Add to favorites
    @PostMapping("/add")
    public String addToFavorite(@RequestParam(required = false) Long userId,
                                @RequestParam Long productId,
                                RedirectAttributes redirectAttributes) {
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "User ID is missing. Please log in.");
            return "redirect:/login";  // Redirect to login page if user is not logged in
        }
        favoriteService.addToFavorite(userId, productId);
        redirectAttributes.addFlashAttribute("message", "Product added to favorites");
        return "redirect:/favorites?userId=" + userId;
    }

    // Remove from favorites
    @PostMapping("/remove")
    public String removeFavorite(@RequestParam Long userId, @RequestParam Long productId, RedirectAttributes redirectAttributes) {
        try {
            favoriteService.removeFromFavorite(userId, productId);
            redirectAttributes.addFlashAttribute("message", "Product removed from favorites");
            return "redirect:/favorites?userId=" + userId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove favorite: " + e.getMessage());
            return "redirect:/favorites?userId=" + userId;
        }
    }
}