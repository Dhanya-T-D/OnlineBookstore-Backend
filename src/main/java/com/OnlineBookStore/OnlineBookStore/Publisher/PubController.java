package com.OnlineBookStore.OnlineBookStore.Publisher;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

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

//    upload license image



    @PostMapping(path = "/uploadLicense")
    public ResponseEntity<String> uploadLicense(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("File uploaded successfully: " + path.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed");
        }
    }



//    publisher login
//    @PostMapping(path = "/login")
//    public ResponseEntity<?>plogin(@RequestParam String email,@RequestParam String password){
//        try{
//            return pubService.pubLogin(email,password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    add book
    @PostMapping(path = "/addBook")
    public ResponseEntity<?>addbook(@RequestBody BookModel bookModel){
        try{
            return pubService.addBooks(bookModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


// update book

    @PutMapping(path = "/updateBookDetails")
    public ResponseEntity<?>updateBook(@RequestParam Long bookId, @RequestParam Long pubId,@RequestBody UpdateBookDto updateBookDto){
        try{
            return pubService.updateBook(bookId,pubId,updateBookDto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    --------------------delete book----------------------------------------------------

    @DeleteMapping(path = "/delbook")
    public ResponseEntity<?>delBook(@RequestParam Long book_id,@RequestParam Long pub_id){
        try{
            return  pubService.bookDeletion(book_id, pub_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



























