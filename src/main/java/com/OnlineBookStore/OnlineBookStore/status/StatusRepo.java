package com.OnlineBookStore.OnlineBookStore.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StatusRepo extends JpaRepository<StatusModel,Long> {

}
