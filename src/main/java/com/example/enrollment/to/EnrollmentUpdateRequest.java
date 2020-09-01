package com.example.enrollment.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentUpdateRequest {

    private String uname;

    private String fname;

    private String lname;

    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Email
    private String email;

    private String imgUrl;

}
