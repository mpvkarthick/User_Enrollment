package com.example.enrollment.model;

import com.example.enrollment.entity.UserProfile;
import com.example.enrollment.to.EnrollmentTo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentResponse {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Description")
    private List<Description> descriptions;

    @JsonProperty("Profile")
    private EnrollmentTo enrollmentTo;

    public EnrollmentResponse(String status, List<Description> descriptions){
        this.status = status;
        this.descriptions = descriptions;
    }
    public EnrollmentResponse(String status, List<Description> descriptions,EnrollmentTo enrollmentTo){
        this.status = status;
        this.descriptions = descriptions;
        this.enrollmentTo = enrollmentTo;
    }

    public EnrollmentResponse(){

    }
}
