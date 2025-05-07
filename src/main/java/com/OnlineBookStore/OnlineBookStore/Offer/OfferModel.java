package com.OnlineBookStore.OnlineBookStore.Offer;

import com.OnlineBookStore.OnlineBookStore.Publisher.PubModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.LocalDate;

@CrossOrigin
@Entity
@Data
@Table(name = "Offer")
public class OfferModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "offerId")
//    private Long offerId;
//
//    @Column(nullable = false)
//    private String offerName;
//
//    @Column(nullable = false)
//    private double discountPercentage;
//
//    @Column(nullable = false)
//    private LocalDate offerStartDate;
//
//    @Column(nullable = false)
//    private LocalDate offerEndDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId")
    private Long offerId;

    @Column(name = "offerName")
    private String offerName;

    @Column(name = "discountPercentage")
    private double discountPercentage;

    @Column(name = "offerStartDate")
    private LocalDate offerStartDate;

    @Column(name = "offerEndDate")
    private LocalDate offerEndDate;
//    @ManyToOne
//    @JoinColumn(name = "pubId", nullable = false)
//    private PubModel pubId;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(LocalDate offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public LocalDate getOfferEndDate() {
        return offerEndDate;
    }

    public void setOfferEndDate(LocalDate offerEndDate) {
        this.offerEndDate = offerEndDate;
    }


}
