package com.OnlineBookStore.OnlineBookStore.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {
}
