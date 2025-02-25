package com.OnlineBookStore.OnlineBookStore.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdminRegRepo extends JpaRepository<AdminRegModel,Long> {

    Optional<AdminRegModel> findByEmailAndPassword(String email, String password);

    Optional<AdminRegModel> findByEmail(String email);
}
