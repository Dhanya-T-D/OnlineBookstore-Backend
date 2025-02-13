package com.OnlineBookStore.OnlineBookStore.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepo extends JpaRepository<BookModel,Long> {


    Optional<BookModel> findByBookIdAndPubId(Long bookId, Long pubId);
}
