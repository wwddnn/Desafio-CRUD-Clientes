package com.devsuperior.desafio_crud.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//class ValidationError is subclass of superclass CustomError
public class ValidationError extends CustomError {

    //create a list of FieldMessage
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    //get method
    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError (String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

}
