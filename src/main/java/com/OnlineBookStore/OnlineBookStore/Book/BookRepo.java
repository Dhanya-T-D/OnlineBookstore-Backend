package com.OnlineBookStore.OnlineBookStore.Book;

import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepo extends JpaRepository<BookModel,Long> {


    Optional<BookModel> findByBookIdAndPubId(Long bookId, Long pubId);


  List<BookModel> findByPubId(Long pubId);

    List<BookModel> findByPublishedDate(LocalDate publishedDate);


    List<BookModel> findByBookName(String bookName);
    
    List<BookModel> findByPublishedDateBetween(LocalDate startDate, LocalDate endDate);

   List<BookModel> findBookByCatId(Long catId);

    List<BookModel> findByBookNameContainingIgnoreCase(String bookName);



    List<BookModel> findByPubIdAndCatId(Long pubId, Long catId);

    List<BookModel> findBookByLanguageId(Long languageId);


    List<BookModel> findByPubIdAndPublishedDate(Long pubId, LocalDate publishedDate);

    List<BookModel> findByPubIdAndPublishedDateBetween(Long pubId, LocalDate startDate, LocalDate endDate);


    List<BookModel> findBypubIdAndAuthorContainingIgnoreCase(Long pubId, String author);


    List<BookModel> findByPubIdAndLanguageId(Long pubId, Long languageId);

    List<BookModel> findByAuthorContainingIgnoreCase(String author);


//    List<BookModel> findBookByYear(Integer year);
}
