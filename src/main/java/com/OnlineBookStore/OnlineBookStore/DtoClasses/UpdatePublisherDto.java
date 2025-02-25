package com.OnlineBookStore.OnlineBookStore.DtoClasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublisherDto {

    private String pub_name;
    private Long phone;
    private String email;

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
}
