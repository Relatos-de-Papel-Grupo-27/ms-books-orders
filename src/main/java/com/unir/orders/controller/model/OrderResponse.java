package com.unir.orders.controller.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.unir.orders.facade.model.Account;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "totalAmount",
        "status",
        "createdAt",
        "account"
})
@Data
@Builder
public class OrderResponse {

    private Long id;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private Account account;
}