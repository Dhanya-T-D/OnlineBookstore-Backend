package com.OnlineBookStore.OnlineBookStore.Book;

import com.OnlineBookStore.OnlineBookStore.Offer.OfferModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.LocalDate;

@CrossOrigin
@Entity
@Table(name = "BookTable")
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "pub_id")
    private Long pubId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "coverImage")
    private byte[] coverImage;


    @Column(name = "cat_id")
    private Long catId;

    @Column(name = "price")
    private Double price;

    @Column(name = "publishedDate")
    private LocalDate publishedDate;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "languageId")
    private Long languageId;


    @Column(name = "availableCopies")
    private Integer availableCopies;

//    @Column(name = "discountedPrice ")
//    private double discountedPrice;



    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPubId() {
        return pubId;
    }

    public void setPubId(Long pubId) {
        this.pubId = pubId;
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


    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

//    public double getDiscountedPrice() {
//        return discountedPrice;
//    }
//
//    public void setDiscountedPrice(double discountedPrice) {
//        this.discountedPrice = discountedPrice;
//    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }
}
