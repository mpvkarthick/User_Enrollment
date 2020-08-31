package com.example.enrollment.to;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentRequest {


    @NotBlank
    private String uname;

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Email
    private String email;

    @NotBlank
    private String imgUrl;

    @NotBlank
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private String pwd;

}
