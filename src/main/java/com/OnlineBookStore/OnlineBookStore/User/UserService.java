package com.OnlineBookStore.OnlineBookStore.User;

import com.OnlineBookStore.OnlineBookStore.Admin.AdminRegModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageModel;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageRepo;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import com.OnlineBookStore.OnlineBookStore.Publisher.PubRepo;
import com.OnlineBookStore.OnlineBookStore.Role.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
                if (pubModelOptional.isPresent()) {
                    PubModel pubModel = pubModelOptional.get();
                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
                }

                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
                bookDetailsDto.setPrice(bookModel.getPrice());

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

                bookDetailsDtoList.add(bookDetailsDto);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
    }

////     search book by year
//    public ResponseEntity<List<BookDetailsDto>> searchByYear(Integer year) {
//        List<BookModel> bookModels = bookRepo.findBookByYear(year);
//        List<BookDetailsDto> bookDetailsDtoList = new ArrayList<>();
//        if (!bookModels.isEmpty()) {
//            for (BookModel bookModel : bookModels) {
//                BookDetailsDto bookDetailsDto = new BookDetailsDto();
//                bookDetailsDto.setBookName(bookModel.getBookName());
//                bookDetailsDto.setAuthor(bookModel.getAuthor());
//                bookDetailsDto.setEdition(bookModel.getEdition());
//                bookDetailsDto.setPublishedDate(bookModel.getPublishedDate());
//                Optional<LanguageModel> optionalLanguageModel = languageRepo.findById(bookModel.getLanguageId());
//                if (optionalLanguageModel.isPresent()) {
//                    LanguageModel languageModel = optionalLanguageModel.get();
//                    bookDetailsDto.setLanguage(languageModel.getLanguageName());
//                }
//
//
//                Optional<CategoryModel> categoryModelOptional = categoryRepo.findById(bookModel.getCatId());
//                if (categoryModelOptional.isPresent()) {
//                    CategoryModel categoryModel = categoryModelOptional.get();
//                    bookDetailsDto.setCategory(categoryModel.getCatName());
//                }
//
//                Optional<PubModel> pubModelOptional = pubRepo.findById(bookModel.getPubId());
//                if (pubModelOptional.isPresent()) {
//                    PubModel pubModel = pubModelOptional.get();
//                    bookDetailsDto.setPublisherName(pubModel.getPub_name());
//                }
//
//                bookDetailsDto.setAvailableCopies(bookModel.getAvailableCopies());
//                bookDetailsDto.setPrice(bookModel.getPrice());
//
//                bookDetailsDtoList.add(bookDetailsDto);
//            }
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//
//        return new ResponseEntity<>(bookDetailsDtoList, HttpStatus.OK);
//    }
}
