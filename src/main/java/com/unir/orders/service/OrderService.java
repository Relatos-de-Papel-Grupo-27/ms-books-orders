package com.unir.orders.service;

import com.unir.orders.data.model.Order;
import com.unir.orders.controller.model.OrderResponse;

import java.util.List;

public interface OrderService {
	

	OrderResponse getOrderById(Long id);

	List<Order> getOrders();

}
