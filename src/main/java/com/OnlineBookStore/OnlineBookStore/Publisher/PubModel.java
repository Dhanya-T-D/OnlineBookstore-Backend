package com.OnlineBookStore.OnlineBookStore.Publisher;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "Publisher_Registration")
@Data
@CrossOrigin
public class PubModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pubId")
    private Long pubId;

    @Column(name = "roleid")
    private Long roleid;

    @Column(name = "pub_name")
    private String pub_name;


    @Column(name = "phone")
    private Long phone;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Embedded
    private Address address;

    @Column(name = "password")
    private String password;

    @Column(name = "license_no")
    private String license_no;

    @Column(name = "statusId")
    private Long statusId;

//    @Column(name = "licenseImage")
//    private  byte[] licenseImage;

    public PubModel(){
        this.roleid=2L;
        this.statusId=1L;
    }

    public PubModel(String email,String password){
        this.email=email;
        this.password=password;
    }

    public Long getPubId() {
        return pubId;
    }

    public void setPubId(Long pubId) {
        this.pubId = pubId;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

//    public byte[] getLicenseImage() {
//        return licenseImage;
//    }
//
//    public void setLicenseImage(byte[] licenseImage) {
//        this.licenseImage = licenseImage;
//    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
