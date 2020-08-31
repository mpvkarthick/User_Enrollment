package com.example.enrollment.service;

import com.example.enrollment.exception.EnrollmentException;
import com.example.enrollment.model.EnrollmentResponse;
import com.example.enrollment.to.EnrollmentTo;
import org.springframework.http.ResponseEntity;

public interface EnrollmentService {

    ResponseEntity<EnrollmentResponse> createProfile(EnrollmentTo enrollmentTo) throws EnrollmentException;

    ResponseEntity<EnrollmentResponse> deleteProfile(EnrollmentTo enrollmentTo) throws  EnrollmentException;

    ResponseEntity<EnrollmentResponse> updatePassword(EnrollmentTo enrollmentTo) throws EnrollmentException;

    ResponseEntity<EnrollmentResponse> updateProfile(EnrollmentTo enrollmentTo) throws  EnrollmentException;
}
