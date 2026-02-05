package com.unir.orders.service;

import com.unir.orders.data.model.Order;
import com.unir.orders.controller.model.OrderResponse;

import java.util.List;

public interface OrderService {


	OrderResponse getOrderById(Long id);

	Order createOrder(Order order);

	List<Order> getOrders();

	void deleteOrder(Long id);

	Order updateOrder(Long id, Order order);
}
