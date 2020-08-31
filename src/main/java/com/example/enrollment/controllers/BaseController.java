package com.example.enrollment.controllers;

import com.example.enrollment.model.EnrollmentResponse;

public class BaseController {

    protected EnrollmentResponse enrollmentResponse;

    public BaseController(){

        enrollmentResponse = new EnrollmentResponse();
        enrollmentResponse.setStatus("Success");
    }
}
