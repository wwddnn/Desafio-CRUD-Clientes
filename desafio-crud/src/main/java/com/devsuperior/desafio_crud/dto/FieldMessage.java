package com.devsuperior.desafio_crud.dto;

public class FieldMessage {

    //attributes
    private String fieldName;
    private String message;

    //constructor
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    //get methods
    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
