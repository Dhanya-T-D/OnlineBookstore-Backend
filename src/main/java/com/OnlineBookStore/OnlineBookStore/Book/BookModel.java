package com.OnlineBookStore.OnlineBookStore.Book;

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
    private String book_name;

    @Column(name = "author")
    private String author;

    @Column(name = "cover_image")
    private byte[] cover_image;

    @Column(name = "cat_id")
    private Long cat_id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "publishedDate")
    private LocalDate publishedDate;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "language")
    private String language;

    @Column(name = "availableCopies")
    private Integer availableCopies;

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

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getCover_image() {
        return cover_image;
    }

    public void setCover_image(byte[] cover_image) {
        this.cover_image = cover_image;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
