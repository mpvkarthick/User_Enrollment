package com.example.enrollment.service;

import com.example.enrollment.entity.UserProfile;
import com.example.enrollment.exception.EnrollmentException;
import com.example.enrollment.model.EnrollmentResponse;
import com.example.enrollment.repository.UserProfileRepository;
import com.example.enrollment.to.EnrollmentTo;
import com.example.enrollment.util.EnrollmentHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;

@Service
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public ResponseEntity<EnrollmentResponse> createProfile(EnrollmentTo enrollmentTo) throws EnrollmentException {


        try {
            int age = getAge(enrollmentTo);
            byte[] salt = EnrollmentHasher.getSalt();
            String hashedPassword = EnrollmentHasher.getSecurePassword(enrollmentTo.getPwd(), salt);
            UserProfile userProfile = new UserProfile(enrollmentTo.getUname(), enrollmentTo.getFname(), enrollmentTo.getLname(),
                    enrollmentTo.getGender(), enrollmentTo.getDob(), age, enrollmentTo.getEmail(), EnrollmentHasher.bytesToHex(salt), hashedPassword, enrollmentTo.getImgUrl());

            userProfileRepository.save(userProfile);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new EnrollmentException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while persisting data. Please contact System Administrator");
        }
        return prepareResponse();
    }

    @Override
    public ResponseEntity<EnrollmentResponse> deleteProfile(EnrollmentTo enrollmentTo) throws EnrollmentException {
        try {
            UserProfile profile = userProfileRepository.findByUname(enrollmentTo.getUname());
            if (null == profile) {
                throw new EnrollmentException(HttpStatus.BAD_REQUEST.value(), "Invalid Email Address");
            }
            userProfileRepository.deleteById(profile.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new EnrollmentException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while deleting data. Please contact System Administrator");
        }
        return prepareResponse();
    }

    @Override
    public ResponseEntity<EnrollmentResponse> updatePassword(EnrollmentTo enrollmentTo) throws EnrollmentException {
        try {

            byte[] salt = EnrollmentHasher.getSalt();
            String hashedPassword = EnrollmentHasher.getSecurePassword(enrollmentTo.getPwd(), salt);
            UserProfile profile = userProfileRepository.findByUname(enrollmentTo.getUname());
            if (null == profile) {
                throw new EnrollmentException(HttpStatus.BAD_REQUEST.value(), "Invalid Email Address");
            }
            userProfileRepository.updatePassword(hashedPassword, EnrollmentHasher.bytesToHex(salt), profile.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new EnrollmentException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while persisting data. Please contact System Administrator");
        }
        return prepareResponse();
    }

    @Override
    public ResponseEntity<EnrollmentResponse> updateProfile(EnrollmentTo enrollmentTo) throws EnrollmentException {

        try {
        	UserProfile profile = userProfileRepository.findByUname(enrollmentTo.getUname());
            if (null == profile) {
                throw new EnrollmentException(HttpStatus.BAD_REQUEST.value(), "Invalid Email Address");
            }
            profile.setFname(null != enrollmentTo.getFname() ? enrollmentTo.getFname() : profile.getFname());
            profile.setLname(null != enrollmentTo.getLname() ? enrollmentTo.getLname() : profile.getLname());
            profile.setDob(null != enrollmentTo.getDob() ? enrollmentTo.getDob() : profile.getDob());
            profile.setGender(null != enrollmentTo.getGender() ? enrollmentTo.getGender() : profile.getGender());
            profile.setAge(null != enrollmentTo.getDob() ? getAge(enrollmentTo) : profile.getAge());
            userProfileRepository.save(profile);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new EnrollmentException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while persisting data. Please contact System Administrator");
        }
        return prepareResponse();
    }

    private static ResponseEntity<EnrollmentResponse> prepareResponse() {
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse("Success", null);
        return new ResponseEntity<>(enrollmentResponse, HttpStatus.OK);
    }

    private static int getAge(EnrollmentTo enrollmentTo) throws ParseException {
        Instant instant = enrollmentTo.getDob().toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        Period period = Period.between(zone.toLocalDate(), LocalDate.now());
        return period.getYears();
    }
}
