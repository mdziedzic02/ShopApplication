package com.app.shop.dto;

import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationFailureResponse {

    private final List<FieldError> fieldErrors;

    public ValidationFailureResponse(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
