package com.dgpad.project.controller;

import com.dgpad.project.DTO.response.request.CreateOrderDTO;
import com.dgpad.project.DTO.response.request.UpdateOrderDTO;
import com.dgpad.project.model.Order;
import com.dgpad.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService OrderService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = OrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order_list"; // Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        Order order = OrderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order_detail"; // Thymeleaf template name
    }

    @GetMapping("/new")
    public String createOrderForm(Model model) {
        model.addAttribute("createOrderDTO", new CreateOrderDTO());
        return "order_create"; // Thymeleaf template name
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute CreateOrderDTO orderDTO) {
        OrderService.createOrder(orderDTO);
        return "redirect:/orders"; // Redirect to order list
    }

    @GetMapping("/{id}/edit")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Order order = OrderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order_edit"; // Thymeleaf template name
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable Long id, @ModelAttribute UpdateOrderDTO orderDTO) {
        OrderService.updateOrder(id, orderDTO);
        return "redirect:/orders"; // Redirect to order list
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        OrderService.deleteOrder(id);
        return "redirect:/orders"; // Redirect to order list
    }
}
