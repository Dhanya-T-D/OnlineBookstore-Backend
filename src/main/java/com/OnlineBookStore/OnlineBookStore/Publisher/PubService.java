package com.OnlineBookStore.OnlineBookStore.Publisher;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Book.BookRepo;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryModel;
import com.OnlineBookStore.OnlineBookStore.Category.CategoryRepo;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.BookDetailsDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdateBookDto;
import com.OnlineBookStore.OnlineBookStore.DtoClasses.UpdatePublisherDto;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageModel;
import com.OnlineBookStore.OnlineBookStore.Language.LanguageRepo;
import com.OnlineBookStore.OnlineBookStore.User.UserRepo;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PubService {
    @Autowired
    private PubRepo pubRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    //    Publisher Registration
    public ResponseEntity<?> publisherReg(PubModel pubModel) {
        PubModel pubModel1 = new PubModel();
        pubModel1.setRoleid(pubModel.getRoleid());
        pubModel1.setPub_name(pubModel.getPub_name());
        pubModel1.setAddress(pubModel.getAddress());
        pubModel1.setPhone(pubModel.getPhone());
        if (isEmailAlreadyRegistered(pubModel.getEmail()) || isEmailAlreadyRegistered(pubModel.getEmail())) {
            return new ResponseEntity<>("Email is Already Registered", HttpStatus.BAD_REQUEST);
        }
        pubModel1.setEmail(pubModel.getEmail());
        pubModel1.setPassword(pubModel.getPassword());
        pubModel1.setLicense_no(pubModel.getLicense_no());
//        pubModel1.setLicenseImage(pubModel.getLicenseImage());

        pubRepo.save(pubModel1);
        return new ResponseEntity<>(pubModel1, HttpStatus.OK);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return (pubRepo.existsByEmail(email) || userRepo.existsByEmail(email));
    }


    //    add books
    public ResponseEntity<?> addBooks(BookModel bookModel) {
        BookModel bookModel1 = new BookModel();
        bookModel1.setPubId(bookModel.getPubId());
        bookModel1.setBookName(bookModel.getBookName());
        bookModel1.setAuthor(bookModel.getAuthor());
//       bookModel1.setCover_image(bookModel.getCover_image());
        bookModel1.setCatId(bookModel.getCatId());
        bookModel1.setPrice(bookModel.getPrice());
        bookModel1.setPublishedDate(bookModel.getPublishedDate());
        bookModel1.setEdition(bookModel.getEdition());
        bookModel1.setLanguageId(bookModel.getLanguageId());
        bookModel1.setAvailableCopies(bookModel.getAvailableCopies());
        bookRepo.save(bookModel1);
        return new ResponseEntity<>(bookModel1, HttpStatus.OK);
    }

// update book details

    public ResponseEntity<?> updateBook(Long bookId, Long pubId, UpdateBookDto updateBookDto) {
        Optional<BookModel> bookModelOptional = bookRepo.findByBookIdAndPubId(bookId, pubId);

        if (bookModelOptional.isPresent()) {

            BookModel bookModel = bookModelOptional.get();
            bookModel.setAuthor(updateBookDto.getAuthor());
            bookModel.setAvailableCopies(updateBookDto.getAvailableCopies());
            bookModel.setBookName(updateBookDto.getBookName());
            bookModel.setCatId(updateBookDto.getCatId());
            bookModel.setPrice(updateBookDto.getPrice());
            bookModel.setPublishedDate(updateBookDto.getPublishedDate());
            bookModel.setEdition(updateBookDto.getEdition());
            bookModel.setLanguageId(updateBookDto.getLanguageId());
            bookRepo.save(bookModel);
            return new ResponseEntity<>("Book Details Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book Not Found", HttpStatus.NOT_FOUND);
    }


//    -------------- book deletion-----------------------------

    public ResponseEntity<?> bookDeletion(Long bookId, Long pubId) {
        Optional<BookModel> bookModelOptional = bookRepo.findByBookIdAndPubId(bookId, pubId);
        if (bookModelOptional.isPresent()) {
            BookModel bookModel = bookModelOptional.get();
            bookRepo.delete(bookModel);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    //    ------- update publisher details
    public ResponseEntity updatePublisher(Long pubId, UpdatePublisherDto updatePublisherDto) {
        Optional<PubModel> pubModelOptional = pubRepo.findById(pubId);
        if (pubModelOptional.isPresent()) {
            PubModel pubModel = pubModelOptional.get();
            pubModel.setPub_name(updatePublisherDto.getPub_name());
            pubModel.setPhone(updatePublisherDto.getPhone());
            pubModel.setEmail(updatePublisherDto.getEmail());
            pubRepo.save(pubModel);
            return new ResponseEntity<>("Details Updated Successfully", HttpStatus.OK);
        } else
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    display all books of particular publisher

    public ResponseEntity<List<BookDetailsDto>> displayAllBooksOfPublisher(Long pubId) {
        List<BookModel> bookModels = bookRepo.findByPubId(pubId);
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

//    search book by category -  for a particular publisher


    public ResponseEntity<List<BookDetailsDto>> searchBookByCategory(Long pubId, Long catId) {
        List<BookModel> bookModels = bookRepo.findByPubIdAndCatId(pubId,catId);
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

//    search book by published date - for publisher

    public ResponseEntity<List<BookDetailsDto>> searchByPublishedDate(Long pubId, LocalDate publishedDate) {
        List<BookModel> bookModels = bookRepo.findByPubIdAndPublishedDate(pubId,publishedDate);
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

    //    search book - between published date - for a particular publisher
    public ResponseEntity<List<BookDetailsDto>> searchBetweenPublishedDate(Long pubId, LocalDate startDate, LocalDate endDate) {
        List<BookModel>bookModels=bookRepo.findByPubIdAndPublishedDateBetween(pubId,startDate,endDate);
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

    //    search book by author name for a particular publisher
    public ResponseEntity<List<BookDetailsDto>> searchAuthorName(Long pubId, String author) {
        List<BookModel>bookModels=bookRepo.findBypubIdAndAuthorContainingIgnoreCase(pubId,author);
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

// search book by language - for publisher
    public ResponseEntity<List<BookDetailsDto>> searchLanguage(Long pubId, Long languageId) {
        List<BookModel>bookModels=bookRepo.findByPubIdAndLanguageId(pubId,languageId);
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



}
