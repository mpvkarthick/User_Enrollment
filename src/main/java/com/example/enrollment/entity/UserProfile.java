package com.example.enrollment.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_PROFILE")
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="UNAME", unique = true)
    private String uname;

    @Column(name="FNAME")
    private String fname;

    @Column(name="LNAME")
    private String lname;

    @Column(name="AGE")
    private int age;

    @Column(name="GENDER")
    private String gender;

    @Column(name="DOB")
    private Date dob;

    @Column(name="EMAIL")
    private String email;

    @Column(name="SALT")
    private String salt;

    @Column(name="HPWD")
    private String hpwd;

    @Column(name = "IMG_URL")
    private String imgUrl;

    public UserProfile(String uname, String fname, String lname, String gender, Date dob, int age, String email, String salt, String hpwd, String imgUrl) {
        this.uname = uname;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.email = email;
        this.salt = salt;
        this.hpwd = hpwd;
        this.imgUrl = imgUrl;
    }
    public UserProfile(){

    }
}
