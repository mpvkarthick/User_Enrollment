package com.example.enrollment.model;

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

    public EnrollmentResponse(String status, List<Description> descriptions){
        this.status = status;
        this.descriptions = descriptions;
    }

    public EnrollmentResponse(){

    }
}
