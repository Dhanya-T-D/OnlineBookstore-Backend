package com.OnlineBookStore.OnlineBookStore.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepo extends JpaRepository<RoleModel,Long> {
}
