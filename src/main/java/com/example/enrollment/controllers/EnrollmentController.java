package com.example.enrollment.controllers;

import com.example.enrollment.exception.EnrollmentException;
import com.example.enrollment.model.EnrollmentResponse;
import com.example.enrollment.service.EnrollmentService;
import com.example.enrollment.to.EnrollmentCredsRequest;
import com.example.enrollment.to.EnrollmentRequest;
import com.example.enrollment.to.EnrollmentTo;
import com.example.enrollment.to.EnrollmentUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController extends BaseController {

    @Autowired
    private EnrollmentService enrollmentService;

    @RequestMapping(value = "/profile/", method = RequestMethod.POST)
    public ResponseEntity<EnrollmentResponse>  createProfile(@Valid @RequestBody EnrollmentRequest enrollmentRequest) throws EnrollmentException{
        EnrollmentTo enrollmentTo =  new EnrollmentTo();
        BeanUtils.copyProperties(enrollmentRequest,enrollmentTo);
        return enrollmentService.createProfile(enrollmentTo);
    }


    @RequestMapping(value = "/profile/", method = RequestMethod.PUT)
    public ResponseEntity<EnrollmentResponse>  updateProfile(@Valid @RequestBody EnrollmentUpdateRequest enrollmentRequest, @NotBlank @RequestParam(value="uname",required =true) String uname) throws EnrollmentException{
        EnrollmentTo enrollmentTo =  new EnrollmentTo();
        BeanUtils.copyProperties(enrollmentRequest,enrollmentTo);
        enrollmentTo.setUname(uname);
        return enrollmentService.updateProfile(enrollmentTo);
    }

    @RequestMapping(value = "/profile/", method = RequestMethod.DELETE)
    public ResponseEntity<EnrollmentResponse>  deleteProfile(@NotBlank @RequestParam(value="uname",required =true) String uname) throws EnrollmentException{
        EnrollmentTo enrollmentTo =  new EnrollmentTo();
        enrollmentTo.setUname(uname);
        return enrollmentService.deleteProfile(enrollmentTo);
    }

    @RequestMapping(value = "/profile/creds/", method = RequestMethod.PUT)
    public ResponseEntity<EnrollmentResponse>  updatePassword(@Valid @RequestBody EnrollmentCredsRequest enrollmentRequest, @NotBlank @RequestParam(value="uname",required =true) String uname) throws EnrollmentException{
        EnrollmentTo enrollmentTo =  new EnrollmentTo();
        BeanUtils.copyProperties(enrollmentRequest,enrollmentTo);
        enrollmentTo.setUname(uname);
        return enrollmentService.updateProfile(enrollmentTo);
    }

    @RequestMapping(value = "/profile/", method = RequestMethod.GET)
    public ResponseEntity<EnrollmentResponse>  viewProfile(@NotBlank @RequestParam(value="uname",required =true) String uname) throws EnrollmentException{
        EnrollmentTo enrollmentTo =  new EnrollmentTo();
        enrollmentTo.setUname(uname);
        return enrollmentService.viewProfile(enrollmentTo);
    }

}
