package com.OnlineBookStore.OnlineBookStore.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel,Long> {
}
