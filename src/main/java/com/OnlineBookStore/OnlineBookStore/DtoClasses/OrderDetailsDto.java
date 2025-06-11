package com.OnlineBookStore.OnlineBookStore.DtoClasses;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@CrossOrigin
public class OrderDetailsDto {
    private String bookName;
    private double bookPrice;
    private int shippingCharge;

    private double totalPrice;
    private LocalDate expectedDeliveryDate;
    private String paymentMode;

    public OrderDetailsDto(String bookName, double bookPrice,
                           int shippingCharge, double totalPrice,
                           LocalDate expectedDeliveryDate, String paymentMode) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.paymentMode = paymentMode;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(int shippingCharge) {
        this.shippingCharge = shippingCharge;
    }



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
