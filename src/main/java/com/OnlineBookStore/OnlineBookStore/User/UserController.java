package com.OnlineBookStore.OnlineBookStore.User;

import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/User")

public class UserController {
    @Autowired
    private UserService userService;

//    user registration
    @PostMapping(path = "/add/user")
    public ResponseEntity<?>addusr(@RequestBody UserModel userModel){
        try {
            return userService.addUser(userModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

// list all books for user

    @GetMapping(path = "/list/all/books")
    public ResponseEntity<List<BookDetailsDto>>listAllBooks(){
        return userService.listAllBooks();
    }



//   search book by category name
@GetMapping(path = "/search/Category")
public ResponseEntity<List<BookDetailsDto>>searchByCategory(@RequestParam Long catId){
        return userService.searchByCategory(catId);
}

//    search book by published date

    @GetMapping(path = "/book/Published/date")
    public ResponseEntity<List<BookDetailsDto>>bookByPublishedDate(@RequestParam LocalDate publishedDate){
        return userService.bookByPublishedDate(publishedDate);
    }

//    search book between published date
    @GetMapping(path = "/search/between/dates")
    public ResponseEntity<List<BookDetailsDto>>searchBetweenDates(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        return userService.searchBetweenDates(startDate,endDate);
    }

//    search by book name using contains
    @GetMapping(path = "/search/book/name")
    public ResponseEntity<List<BookDetailsDto>>searchBookName(@RequestParam String bookName){
        return userService.searchBookName(bookName);
    }

// search book by language
    @GetMapping(path = "/search/language")
    public ResponseEntity<List<BookDetailsDto>>searchByLanguage(@RequestParam Long languageId){
        return userService.searchByLanguage(languageId);
    }

//    search by author name

    @GetMapping(path = "/search/author/name")
    public ResponseEntity<List<BookDetailsDto>>searchByAuthor(@RequestParam String author){
        return userService.searchByAuthor(author);
    }

////  search book by year
//@GetMapping(path = "/search/year")
//public ResponseEntity<List<BookDetailsDto>>searchByYear(@RequestParam Integer year){
//    return userService.searchByYear(year);
//}

    // search book by category and language

    @GetMapping("/searchBooksByCatAndLang")
    public ResponseEntity<List<BookDetailsDto>> searchBooksByCategoryAndLanguage( @RequestParam Long catId, @RequestParam Long languageId) {
        return userService.searchBooksByCategoryAndLanguage(catId, languageId);
    }
}


































