package com.OnlineBookStore.OnlineBookStore.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PubRepo extends JpaRepository<PubModel,Long> {


    Optional<PubModel> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
