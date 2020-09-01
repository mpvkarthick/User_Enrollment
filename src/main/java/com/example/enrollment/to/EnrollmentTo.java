package com.example.enrollment.to;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
