package com.unir.orders.data;

import com.unir.orders.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByAccountIdAndBookIdAndStatus(
            Long accountId,
            Long bookId,
            String status
    );
}
