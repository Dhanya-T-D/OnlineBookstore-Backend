package com.OnlineBookStore.OnlineBookStore.Publisher;

import jakarta.persistence.Embeddable;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Embeddable
public class Address {
    private String addressline;
    private String street;
    private Integer pincode;
    private String district;
    private String state;
    private String country;

    public String getAddressline() {
        return addressline;
    }

    public void setAddressline(String addressline) {
        this.addressline = addressline;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
