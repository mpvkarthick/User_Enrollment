package com.example.enrollment.exception;

import com.example.enrollment.model.Description;
import com.example.enrollment.model.EnrollmentResponse;
import com.example.enrollment.util.EnrollmentConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class EnrollmentExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        List<Description> descriptions = bindingResult.getFieldErrors().stream()
                .map(fieldError -> new Description(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(toList());

        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        Description description = new Description(EnrollmentConstants.HEADERS_KEY, ex.getMessage());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        headers.setContentType(MediaType.APPLICATION_JSON);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        Description description = new Description(EnrollmentConstants.MEDIA_TYPE_KEY, ex.getMessage());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        headers.setContentType(MediaType.APPLICATION_JSON);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
    }

    private ResponseEntity<Object> handleAPIException(EnrollmentException ex, WebRequest request) {

        Description description = new Description(ex.getApiName(), ex.getReason());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, null, HttpStatus.valueOf(ex.getErrorCode()), request);
    }

    private ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex, WebRequest request) {
        Description description = new Description("Json Processing Error", ex.getOriginalMessage());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        Description description = new Description("System Issues", ex.getLocalizedMessage());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleApplicationException(Exception ex, WebRequest request) {

        if (ex instanceof EnrollmentException) {
            return handleAPIException((EnrollmentException) ex, request);
        } else if (ex instanceof JsonProcessingException) {
            return handleJsonProcessingException((JsonProcessingException) ex, request);
        } else if (ex instanceof Exception) {
            return handleSystemException(ex, request);
        }
        return null;

    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Description description = new Description(EnrollmentConstants.MEDIA_TYPE_KEY, ex.getMessage());
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(description);
        headers.setContentType(MediaType.APPLICATION_JSON);
        EnrollmentResponse response = new EnrollmentResponse(EnrollmentConstants.FAILURE_KEY, descriptions);
        return handleExceptionInternal(ex, response, headers, status, request);
    }


}
