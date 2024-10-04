package com.dgpad.project.service;

import com.dgpad.project.DTO.response.request.CreateOrderDTO;
import com.dgpad.project.DTO.response.request.UpdateOrderDTO;
import com.dgpad.project.model.Order;
import com.dgpad.project.model.Product;
import com.dgpad.project.repository.OrderRepository;
import com.dgpad.project.repository.ProductRepository;
import com.dgpad.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dgpad.project.model.User;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    @Autowired
    private UserRepository UserRepository;  // Assuming you have a UserRepository

    @Autowired
    private ProductRepository ProductRepository;

    public Order createOrder(CreateOrderDTO orderDTO) {
        Order order = new Order();
        // Ensure the orderDTO has a valid userId and productIds
        order.setUser(UserRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        order.setProducts(ProductRepository.findAllById(orderDTO.getProductIds()));
        order.setStatus(orderDTO.getStatus() != null ? orderDTO.getStatus() : "Pending");
        order.setTotalPrice(calculateTotalPrice(order.getProducts()));
        return OrderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return OrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }



    public Order updateOrder(Long id, UpdateOrderDTO orderDTO) {
        Optional<Order> existingOrderOptional = OrderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setStatus(orderDTO.getStatus()); // Update status

            return OrderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }



    public void deleteOrder(Long id) {
        OrderRepository.deleteById(id);
    }

    private Double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}