package com.example.enrollment.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

public class EnrollmentException extends  Exception {

    @Getter
    @Setter
    protected int errorCode;

    @Getter
    @Setter
    protected String reason;

    @Getter
    @Setter
    protected Collection<String> validationErrors;

    @Getter
    @Setter
    protected String apiName;

    public EnrollmentException(){

    }

    public EnrollmentException(int errorCode, String reason){
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public EnrollmentException(int errorCode, Collection<String> validationErrors) {
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }

    public EnrollmentException(int errorCode, String reason, String apiName) {
        this.errorCode = errorCode;
        this.reason = reason;
        this.apiName = apiName;
    }
}
