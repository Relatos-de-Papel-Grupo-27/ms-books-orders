package com.unir.orders.service;

import com.unir.orders.data.OrderJpaRepository;
import com.unir.orders.data.model.Order;
import com.unir.orders.controller.model.OrderResponse;
import com.unir.orders.facade.AccountsFacade;
import com.unir.orders.facade.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderJpaRepository repository;
  private final AccountsFacade accountsFacade;

  public OrderServiceImpl(OrderJpaRepository repository, AccountsFacade usersFacade) {
    this.repository = repository;
    this.accountsFacade = usersFacade;
  }


  @Override
  public List<Order> getOrders() {
  return repository.findAll();
}

  @Override
  public OrderResponse getOrderById(Long id) {

    Order order = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Order not found with id: " + id));

    Account account = accountsFacade.getAccountById(order.getId());

    return OrderResponse.builder()
            .id(order.getId())
            .totalAmount(order.getTotalAmount())
            .status(order.getStatus())
            .createdAt(order.getCreatedAt())
            .account(account)
            .build();
  }
}
