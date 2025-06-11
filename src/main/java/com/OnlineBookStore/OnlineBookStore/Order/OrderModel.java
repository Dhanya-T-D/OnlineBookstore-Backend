package com.OnlineBookStore.OnlineBookStore.Order;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@Entity
@CrossOrigin
@Table(name = "OrderDetails")
@Data

public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "pubId")
    private Long pubId;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private Long  phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "bookPrice")
    private double bookPrice;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "adminShare")
    private double adminShare;

    @Column(name = "publisherShare")
    private double publisherShare;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "expectedDeliveryDate")
    private LocalDate expectedDeliveryDate;

    @Column(name = "paymentMode")
    private String paymentMode = "Cash on Delivery";

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAdminShare() {
        return adminShare;
    }

    public void setAdminShare(double adminShare) {
        this.adminShare = adminShare;
    }

    public double getPublisherShare() {
        return publisherShare;
    }

    public void setPublisherShare(double publisherShare) {
        this.publisherShare = publisherShare;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
