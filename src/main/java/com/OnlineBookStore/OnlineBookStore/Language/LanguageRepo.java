package com.OnlineBookStore.OnlineBookStore.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<LanguageModel,Long> {
}
