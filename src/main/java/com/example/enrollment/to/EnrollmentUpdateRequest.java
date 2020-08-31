package com.example.enrollment.to;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentUpdateRequest {

	 	@NotBlank
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
