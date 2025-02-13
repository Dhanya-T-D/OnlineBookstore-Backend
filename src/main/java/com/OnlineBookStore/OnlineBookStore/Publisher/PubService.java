package com.OnlineBookStore.OnlineBookStore.Publisher;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import com.OnlineBookStore.OnlineBookStore.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PubService {
    @Autowired
    private PubRepo pubRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

//    Publisher Registration
    public ResponseEntity<?> publisherReg(PubModel pubModel) {
        PubModel pubModel1=new PubModel();
        pubModel1.setRoleid(pubModel.getRoleid());
        pubModel1.setPub_name(pubModel.getPub_name());
        pubModel1.setAddress(pubModel.getAddress());
        pubModel1.setPhone(pubModel.getPhone());
        if (isEmailAlreadyRegistered(pubModel.getEmail())||isEmailAlreadyRegistered(pubModel.getEmail())){
            return new ResponseEntity<>("Email is Already Registered",HttpStatus.BAD_REQUEST);
        }
        pubModel1.setEmail(pubModel.getEmail());
        pubModel1.setPassword(pubModel.getPassword());
        pubModel1.setLicense_no(pubModel.getLicense_no());
//        pubModel1.setLicenseImage(pubModel.getLicenseImage());

        pubRepo.save(pubModel1);
        return new ResponseEntity<>(pubModel1, HttpStatus.OK);
    }
    public boolean isEmailAlreadyRegistered(String email){
        return (pubRepo.existsByEmail(email)||userRepo.existsByEmail(email));
    }
//    upload license image file



// publisher login
//    public ResponseEntity<?> pubLogin(String email, String password) {
//        Optional <PubModel>pubModelOptional=pubRepo.findByEmailAndPassword(email,password);
//        if (pubModelOptional.isPresent()){
//            return new ResponseEntity<>("login Successful",HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>("Incorrect Login Details",HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    add books
    public ResponseEntity<?> addBooks(BookModel bookModel) {
        BookModel bookModel1=new BookModel();
        bookModel1.setPubId(bookModel.getPubId());
        bookModel1.setBook_name(bookModel.getBook_name());
        bookModel1.setAuthor(bookModel.getAuthor());
        bookModel1.setCover_image(bookModel.getCover_image());
        bookModel1.setCat_id(bookModel.getCat_id());
        bookModel1.setPrice(bookModel.getPrice());
        bookModel1.setPublishedDate(bookModel.getPublishedDate());
        bookModel1.setEdition(bookModel.getEdition());
        bookModel1.setLanguage(bookModel.getLanguage());
        bookModel1.setAvailableCopies(bookModel.getAvailableCopies());
        bookRepo.save(bookModel1);
        return new ResponseEntity<>(bookModel1,HttpStatus.OK);
    }

// update book details

    public ResponseEntity<?> updateBook(Long bookId, Long pubId, UpdateBookDto updateBookDto) {
        Optional<BookModel>bookModelOptional=bookRepo.findByBookIdAndPubId(bookId,pubId);

        if (bookModelOptional.isPresent()) {

                BookModel bookModel = bookModelOptional.get();
                bookModel.setAuthor(updateBookDto.getAuthor());
                bookModel.setAvailableCopies((updateBookDto.getAvailable_copies()));
                bookModel.setBook_name(updateBookDto.getBookName());
                bookModel.setCat_id(updateBookDto.getCatId());
                bookModel.setPrice(updateBookDto.getPrice());
                bookModel.setPublishedDate(updateBookDto.getPublished_date());
                bookModel.setEdition(updateBookDto.getEdition());
                bookModel.setLanguage(updateBookDto.getLanguage());
                bookRepo.save(bookModel);
                return new ResponseEntity<>("Book Details Updated Successfully", HttpStatus.OK);
            }
              return new ResponseEntity<>("Book Not Found",HttpStatus.NOT_FOUND);
        }



//    -------------- book deletion-----------------------------

    public ResponseEntity<?> bookDeletion(Long bookId, Long pubId) {
        Optional<BookModel>bookModelOptional=bookRepo.findByBookIdAndPubId(bookId,pubId);
        if(bookModelOptional.isPresent()){
            BookModel bookModel=bookModelOptional.get();
            bookRepo.delete(bookModel);
            return new ResponseEntity<>("Book deleted successfully",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
    }
}
