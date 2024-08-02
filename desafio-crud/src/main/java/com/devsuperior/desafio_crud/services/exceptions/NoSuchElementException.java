package com.devsuperior.desafio_crud.services.exceptions;

public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException (String msg) {
        super(msg);
    }
}
