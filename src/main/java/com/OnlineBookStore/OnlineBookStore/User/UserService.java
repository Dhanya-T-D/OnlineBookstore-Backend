package com.OnlineBookStore.OnlineBookStore.User;

import com.OnlineBookStore.OnlineBookStore.Admin.AdminRegModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UserDetailsDto;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageModel;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageRepo;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubRepo;
import com.OnlineBookStore.OnlineBookStore.Role.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PubRepo pubRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private LanguageRepo languageRepo;


    //     user registration
    public ResponseEntity<?> addUser(UserModel userModel) {

        UserModel userModel1 = new UserModel();
        userModel1.setRoleid(userModel.getRoleid());
        userModel1.setUsername(userModel.getUsername());
        userModel1.setEmail(userModel.getEmail());
        if (isEmailAlreadyRegistered(userModel.getEmail()) || isEmailAlreadyRegistered(userModel.getEmail())) {
            return new ResponseEntity<>("Email is Already Registered", HttpStatus.BAD_REQUEST);
        }

        userModel1.setPhone(userModel.getPhone());
        userModel1.setPassword(userModel.getPassword());
        userRepo.save(userModel1);
        return new ResponseEntity<>(userModel1, HttpStatus.OK);

    }

    public boolean isEmailAlreadyRegistered(String email) {
        return (userRepo.existsByEmail(email) || pubRepo.existsByEmail(email));
    }



//    list all books

    public ResponseEntity<List<BookDetailsDto>> listAllBooks() {
        List<BookModel> bookModels = bookRepo.findAll();
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }

                bookDetailsDto.setPublisherId(bookModel.getPubId());
                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);

    }

//    search books by published date
    public ResponseEntity<List<BookDetailsDto>> bookByPublishedDate(LocalDate publishedDate) {
        List<BookModel> bookModels = bookRepo.findByPublishedDate(publishedDate);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());
                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

//    search book between published dates

    public ResponseEntity<List<BookDetailsDto>> searchBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<BookModel> bookModels = bookRepo.findByPublishedDateBetween(startDate,endDate);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());
                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

// search book by category name
    public ResponseEntity<List<BookDetailsDto>> searchByCategory(Long catId) {
        List<BookModel> bookModels = bookRepo.findBookByCatId(catId);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }


//    search book by name
    public ResponseEntity<List<BookDetailsDto>> searchBookName(String bookName) {
        List<BookModel> bookModels = bookRepo.findByBookNameContainingIgnoreCase(bookName);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

//    search by language
    public ResponseEntity<List<BookDetailsDto>> searchByLanguage(Long languageId) {
        List<BookModel> bookModels = bookRepo.findBookByLanguageId(languageId);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

//     search by author name
    public ResponseEntity<List<BookDetailsDto>> searchByAuthor(String author) {
        List<BookModel> bookModels = bookRepo.findByAuthorContainingIgnoreCase(author);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<BookDetailsDto>> searchBooksByCategoryAndLanguage(Long catId, Long languageId) {

        List<BookModel> bookModels = bookRepo.findBookByCatIdAndLanguageId(catId,languageId);
        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
        if (!bookModels.isEmpty()) {
            for (BookModel bookModel : bookModels) {
                BookDetailsDto bookDetailsDto = new BookDetailsDto();
                bookDetailsDto.setBookId(bookModel.getBookId());
                bookDetailsDto.setBookName(bookModel.getBookName());
                bookDetailsDto.setAuthor(bookModel.getAuthor());
                bookDetailsDto.setLanguageId(bookModel.getLanguageId());

                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
                if (optionalLanguageModel.isPresent()) {
                    LanguageModel languageModel = optionalLanguageModel.get();
                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
                }

                bookDetailsDto.setEdition(bookModel.getEdition());
                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
                bookDetailsDto.setCategoryId(bookModel.getCatId());

                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel = categoryModelOptional.get();
                    bookDetailsDto.setCategory(categoryModel.getCatName());
                }


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());
                bookDetailsDto.setCoverImage(bookModel.getCoverImage());

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

//     edit user details
    public ResponseEntity<?> editUserDetails(Long userid, String username,String email, String phone) {
        Optional<UserModel>userModelOptional=userRepo.findById(userid);
        if (userModelOptional.isPresent()){
            UserModel userModel=userModelOptional.get();
            userModel.setUsername(username);
            userModel.setEmail(email);
            userModel.setPhone(phone);
            userRepo.save(userModel);
            return new ResponseEntity<>("Successful",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Updation failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }

// fetch user details
public ResponseEntity<List<UserDetailsDto>> displayUser(Long userid) {
    Optional<UserModel> userOptional = userRepo.findById(userid);
    List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();

    if (userOptional.isPresent()) {
        UserModel userModel = userOptional.get();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserid(userModel.getUserid());
        userDetailsDto.setUsername(userModel.getUsername());
        userDetailsDto.setRoleid(userModel.getRoleid());
        userDetailsDto.setEmail(userModel.getEmail());
        userDetailsDto.setPhone(userModel.getPhone());
        userDetailsDtoList.add(userDetailsDto);
        return new ResponseEntity<>(userDetailsDtoList, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



}
