package com.OnlineBookStore.OnlineBookStore.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryModel,Long> {


    Optional<CategoryModel> findByCatId(Long catId);
}
