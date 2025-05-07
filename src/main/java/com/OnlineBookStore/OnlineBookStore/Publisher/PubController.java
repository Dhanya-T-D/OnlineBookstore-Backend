package com.OnlineBookStore.OnlineBookStore.Publisher;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
//import com.OnlineBookStore.OnlineBookStore.DtoClasses.AddofferDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdatePublisherDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/publisher")

public class PubController {
    @Autowired
    private PubService pubService;

//    Publisher Registration

    @PostMapping(path = "/Registration")
    public ResponseEntity<?> preg(@RequestBody PubModel pubModel){
        try{
            return pubService.publisherReg(pubModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    add book
    @PostMapping(path = "/add/book")
    public ResponseEntity<?>addbook(@RequestPart BookModel bookModel, @RequestPart MultipartFile coverImage){
        try{
            return pubService.addBooks(bookModel,coverImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

// update book

    @PutMapping(path = "/update/book/details")
    public ResponseEntity<?>updateBook(@RequestPart Long bookId,
                                       @RequestPart Long pubId,
                                       @RequestPart UpdateBookDto updateBookDto,
                                       @RequestPart(value = "coverImage", required = false) MultipartFile coverImage){
        try{
            return pubService.updateBook(bookId,pubId,updateBookDto,coverImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    --------------------delete book----------------------------------------------------

    @DeleteMapping(path = "/delete/book")
    public ResponseEntity<?>delBook(@RequestParam Long bookId,@RequestParam Long pubId){
        try{
            return  pubService.bookDeletion(bookId, pubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    ------- update publisher details ------------
    @PutMapping(path = "/update/publisher")
    public ResponseEntity<?>updatePublisher(@RequestParam Long pubId, @RequestBody UpdatePublisherDto updatePublisherDto){
        try{
            return pubService.updatePublisher(pubId,updatePublisherDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


// list all books of particular publisher

    @GetMapping(path = "/display/Publisher/books")
    public ResponseEntity<List<BookDetailsDto>> displayBooksOfPublisher(@RequestParam Long pubId){
        return pubService.displayAllBooksOfPublisher(pubId);
    }

//    search book by category -  for a particular publisher

    @GetMapping(path = "/search/book/category")
    public ResponseEntity<List<BookDetailsDto>> displayBooksOfPublisher(@RequestParam Long pubId,@RequestParam Long catId){
        return pubService.searchBookByCategory(pubId,catId);
    }


//    search book by published date - for a particular publisher

    @GetMapping(path = "/search/book/date")
    public ResponseEntity<List<BookDetailsDto>> searchByPublishedDate(@RequestParam Long pubId, @RequestParam LocalDate publishedDate){
        return pubService.searchByPublishedDate(pubId,publishedDate);
    }

//    search book - between published date - for a particular publisher

    @GetMapping(path = "/search/book/between/published/date")
    public ResponseEntity<List<BookDetailsDto>>searchBetweenPublishedDate(@RequestParam Long pubId,@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return pubService.searchBetweenPublishedDate(pubId,startDate,endDate);
    }

//    search book by author name for a particular publisher
    @GetMapping(path = "/search/book/author")
    public ResponseEntity<List<BookDetailsDto>>searchAuthorName(@RequestParam Long pubId,@RequestParam String author){
        return pubService.searchAuthorName(pubId,author);
    }

//    search book by name
@GetMapping(path = "/search/book/name")
public ResponseEntity<List<BookDetailsDto>>searchBookName(@RequestParam Long pubId,@RequestParam String bookName){
    return pubService.searchBookName(pubId,bookName);
}

//    search book by language for publisher
@GetMapping(path = "/search/book/language")
    public ResponseEntity<List<BookDetailsDto>>searchLanguage(@RequestParam Long pubId,@RequestParam Long languageId){
        return pubService.searchLanguage(pubId,languageId);
}
// search book by category and language

    @GetMapping("/searchBooksByCatAndLang")
    public ResponseEntity<List<BookDetailsDto>> searchBooksByCategoryAndLanguage(@RequestParam Long pubId, @RequestParam Long catId, @RequestParam Long languageId) {
        return pubService.searchBooksByCategoryAndLanguage(pubId, catId, languageId);
    }


// add offer

//@PostMapping("/add/offer")
//public ResponseEntity<?> addOffer( @RequestParam Long pubId, @RequestBody AddofferDto addofferDto) {
//
//    try{
//        pubService.addOffer(pubId, addofferDto);
//        return new ResponseEntity<>("Successfully added offer",HttpStatus.OK);
//    } catch (Exception e) {
//        e.printStackTrace();
//        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

//    @PostMapping("/add/offer")
//    public ResponseEntity<?> addOffer(@RequestParam Long pubId, @RequestBody AddofferDto addofferDto) {
//        try {
//            pubService.addOffer(pubId, addofferDto);
//            return new ResponseEntity<>("Offer added successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("Error while adding offer", e);
//            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}




























