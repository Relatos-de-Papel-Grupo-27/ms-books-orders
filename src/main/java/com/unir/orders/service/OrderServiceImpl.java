package com.unir.orders.service;

import com.unir.orders.data.OrderJpaRepository;
import com.unir.orders.data.model.Order;
import com.unir.orders.controller.model.OrderResponse;
import com.unir.orders.facade.AccountsFacade;
import com.unir.orders.facade.BooksCatalogueFacade;
import com.unir.orders.facade.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final OrderJpaRepository repository;
  private final AccountsFacade accountsFacade;
  private final BooksCatalogueFacade booksCatalogueFacade;

  public OrderServiceImpl(OrderJpaRepository repository, AccountsFacade usersFacade, BooksCatalogueFacade booksCatalogueFacade) {
    this.repository = repository;
    this.accountsFacade = usersFacade;
      this.booksCatalogueFacade = booksCatalogueFacade;
  }

  @Override
  public Order createOrder(Order order) {

    boolean available =
            booksCatalogueFacade.isBookAvailable(order.getBookId());

    if (!available) {
      throw new RuntimeException("Book not available");
    }

    order.setStatus("VERIFIED");
    order.setCreatedAt(LocalDateTime.now());

    return repository.save(order);
  }

  @Override
  public void deleteOrder(Long id) {

    if (!repository.existsById(id)) {
      throw new RuntimeException("Order not found with id " + id);
    }

    repository.deleteById(id);
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

  @Override
  public Order updateOrder(Long id, Order order) {

    Order existingOrder = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

    existingOrder.setTotalAmount(order.getTotalAmount());
    existingOrder.setStatus(order.getStatus());

    return repository.save(existingOrder);
  }

}
