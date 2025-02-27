package com.OnlineBookStore.OnlineBookStore.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}
