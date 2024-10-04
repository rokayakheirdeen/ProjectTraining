package com.dgpad.project.service;

import com.dgpad.project.model.Favorite;
import com.dgpad.project.model.Product;
import com.dgpad.project.model.User;
import com.dgpad.project.repository.FavoriteRepository;
import com.dgpad.project.repository.ProductRepository;
import com.dgpad.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;

    // Constructor for FavoriteService
    @Autowired
    public FavoriteService(UserRepository userRepository, FavoriteRepository favoriteRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
    }

    public void addToFavorite(Long userId, Long productId) {
        if (favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new IllegalStateException("Product is already in favorites");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Favorite favorite = new Favorite(user, product);
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorite(Long userId, Long productId) {
        if (!favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new IllegalStateException("Product not in favorites");
        }
        favoriteRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public List<Favorite> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
