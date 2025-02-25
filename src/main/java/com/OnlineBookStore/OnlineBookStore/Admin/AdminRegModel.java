package com.OnlineBookStore.OnlineBookStore.Admin;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Entity
@Table(name = "Admin_Registration")
@Data

public class AdminRegModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long admin_id;


    @Column(name="admin_name")
    private String admin_name;

    @Column(name = "roleid")
    private Long roleid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public AdminRegModel(){
        this.roleid=1L;
    }
    public AdminRegModel(String email,String pasword){
        this.email=email;
        this.password=getPassword();

    }
    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
