package com.example.enrollment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Description {

    @JsonProperty("Field")
    private String field;

    @JsonProperty("Error Description")
    private String message;

    public Description(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
