package com.dgpad.project.DTO.response.request;

import lombok.Getter;
import lombok.Setter;

public class UpdateOrderDTO {

    private String status;
    private Double totalPrice;  // Add totalPrice to the DTO

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
