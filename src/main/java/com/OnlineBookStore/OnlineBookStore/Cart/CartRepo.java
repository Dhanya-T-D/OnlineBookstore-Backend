package com.OnlineBookStore.OnlineBookStore.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Long> {



    Optional<Cart> findByUserModel_Userid(Long userId);
}
