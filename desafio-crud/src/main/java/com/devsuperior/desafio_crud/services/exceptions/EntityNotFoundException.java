package com.devsuperior.desafio_crud.services.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException (String msg) {
        super(msg);
    }
}
