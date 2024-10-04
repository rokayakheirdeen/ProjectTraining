package com.dgpad.project.service;

import com.dgpad.project.model.Product;
import com.dgpad.project.repository.ProductRepository;
import com.dgpad.project.DTO.response.request.CreateProductDTO;
import com.dgpad.project.DTO.response.request.UpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(CreateProductDTO product) {
        return productRepository.save(new Product(product.getName(), product.getPrice(), product.getQuantity()));
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, UpdateProductDTO updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(existingProduct);
        } else {
            return null; // Product not found
        }
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public String findProduct(String productId) {
        return productId;
    }
}
