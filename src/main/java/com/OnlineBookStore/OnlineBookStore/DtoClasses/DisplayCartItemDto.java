package com.OnlineBookStore.OnlineBookStore.DtoClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class DisplayCartItemDto {

    private Long bookId;
    private String bookName;
    private Double price;
    private int quantity;

    @Lob
    private byte[] coverImage;

    public DisplayCartItemDto(Long bookId, String bookName, Double price, int quantity, byte[] coverImage) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.coverImage=coverImage;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }
}
