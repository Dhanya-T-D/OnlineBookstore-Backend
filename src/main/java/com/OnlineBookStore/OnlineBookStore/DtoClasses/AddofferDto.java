package com.OnlineBookStore.OnlineBookStore.DtoClasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor

public class AddofferDto {

    private String offerName;
    private Double discountPercentage;
    private LocalDate offerStartDate;
    private LocalDate offerEndDate;
//    private Long pubId;

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
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

//    public Long getPubId() {
//        return pubId;
//    }
//
//    public void setPubId(Long pubId) {
//        this.pubId = pubId;
//    }
}
