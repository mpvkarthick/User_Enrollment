package com.example.enrollment.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentTo {


    private String uname;

    private String fname;

    private String lname;

    private String gender;

    private Date dob;

    private String email;

    private String pwd;

    private String imgUrl;

}
