package com.dgpad.project.repository;

import com.dgpad.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long fromString);
    // Remove the custom method findAllById as JpaRepository already provides it
}
