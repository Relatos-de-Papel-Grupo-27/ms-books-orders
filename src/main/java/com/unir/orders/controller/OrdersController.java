package com.unir.orders.controller;

import com.unir.orders.data.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.orders.service.OrderService;
import com.unir.orders.controller.model.OrderResponse;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService service;

    public OrdersController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {

        return ResponseEntity.ok(service.getOrders());

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse>  getOrderById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {

        Order savedOrder = service.createOrder(order);

        OrderResponse response = OrderResponse.builder()
                .totalAmount(savedOrder.getTotalAmount())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        service.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
        @PathVariable Long id,
        @RequestBody Order order) {

        Order updatedOrder = service.updateOrder(id, order);

        OrderResponse response = OrderResponse.builder()
                .totalAmount(updatedOrder.getTotalAmount())
                .status(updatedOrder.getStatus())
                .createdAt(updatedOrder.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

}
