package com.OnlineBookStore.OnlineBookStore.DtoClasses;

import com.OnlineBookStore.OnlineBookStore.Book.BookModel;
import com.OnlineBookStore.OnlineBookStore.Offer.OfferModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.security.PrivateKey;
import java.time.LocalDate;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDto {
    private Long bookId;
    private String bookName;
    private String author;
    private Long categoryId;
    private String category;
    private Integer edition;
    private Long languageId;
    private String language;
    private String publisherName;
    private LocalDate publishedDate;
    private Integer availableCopies;
    private Double price;
    private Double offerPercentage;
    private Double offerPrice;
    private byte[] coverImage;


//    public BookDetailsDto(BookModel bookModel, OfferModel offerModel) {
//        this.bookId = bookModel.getBookId();
//        this.bookName = bookModel.getBookName();
//        this.categoryId = bookModel.getCatId();
//
//        this.author = bookModel.getAuthor();
//        this.publishedDate = bookModel.getPublishedDate();
//        this.edition = bookModel.getEdition();
//        this.price = bookModel.getPrice();
//
//        if (offerModel != null) {
//            this.offerPercentage= offerModel.getOfferPercentage();
//            this.offerPrice = bookModel.getPrice() - (bookModel.getPrice() * offerModel.getOfferPercentage() / 100);
//        } else {
//            this.offerPercentage = null;
//            this.offerPrice = null;
//        }
//    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(Double offerPercentage) {
        this.offerPercentage = offerPercentage;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }
}
