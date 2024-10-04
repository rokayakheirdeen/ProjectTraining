package com.dgpad.project.controller;

import com.dgpad.project.model.Product;
import com.dgpad.project.DTO.response.request.CreateProductDTO;
import com.dgpad.project.DTO.response.request.UpdateProductDTO;
import com.dgpad.project.repository.ProductRepository;
import com.dgpad.project.service.FavoriteService;
import com.dgpad.project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")  // Updated base path to reflect new URL structure
public class ProductController {

    @Autowired
    private ProductService ProductService;
    @Autowired
    private FavoriteService FavoriteService;  //

    @PostMapping("/create")
    public String createProduct(@ModelAttribute CreateProductDTO product, Model model) {
        try {
            Product savedProduct = ProductService.saveProduct(product);
            model.addAttribute("success", "Product created successfully");
            return "redirect:/product";  // Redirect to the list of products
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create product: " + e.getMessage());
            return "create";  // Display the product creation form again with error
        }
    }

    @GetMapping
    public String getAllProducts(Model model, @RequestParam(required = false) Long userId) {
        try {
            List<Product> products = ProductService.getAllProducts();
            model.addAttribute("products", products);

            if (userId != null) {
                model.addAttribute("userId", userId);  // Add userId if available
            } else {
                model.addAttribute("guest", true);  // Use a flag to indicate guest
            }

            return "list";  // View: templates/list.html
        } catch (Exception e) {
            model.addAttribute("error", "Failed to retrieve products: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        try {
            Product product = ProductService.getProductById(id);
            model.addAttribute("product", product);
            return "detail";  // View: templates/detail.html
        } catch (Exception e) {
            model.addAttribute("error", "Failed to retrieve product: " + e.getMessage());
            return "error";
        }
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("createProductDTO", new CreateProductDTO());
        return "create";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = ProductService.getProductById(id);
        model.addAttribute("product", product);
        return "edit";
    }


    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductDTO product, Model model) {
        try {
            Product updatedProduct = ProductService.updateProduct(id, product);
            model.addAttribute("success", "Product updated successfully");
            return "redirect:/product";  // Redirect to the list of products
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update product: " + e.getMessage());
            return "edit";  // Show the product editing form again
        }
    }


    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model) {
        try {
            ProductService.deleteProduct(id);
            model.addAttribute("success", "Product deleted successfully");
            return "redirect:/product";  // Redirect to the list of products
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete product: " + e.getMessage());
            return "error";  // General error page
        }
    }

    @PostMapping("/add-to-favorite/{productId}")
    public String addToFavorite(@PathVariable Long productId, @RequestParam Long userId, Model model) {
        FavoriteService.addToFavorite(userId, productId);
        model.addAttribute("success", "Product added to favorites!");
        return "redirect:/product";
    }

}
