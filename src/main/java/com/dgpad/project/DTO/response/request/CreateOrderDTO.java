package com.dgpad.project.DTO.response.request;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {
    private Long userId;
    private List<Long> productIds;
    private String status;
    // Add any other fields as needed

    // Getters and Setters
    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds(){
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
