package com.unir.orders.controller;

import com.unir.orders.data.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.orders.service.OrderService;
import com.unir.orders.controller.model.OrderResponse;

import java.util.List;

@RestController
@Slf4j
public class OrdersController {

    private final OrderService service;

    public OrdersController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {

        return ResponseEntity.ok(service.getOrders());

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponse>  getOrderById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getOrderById(id));
    }

}
