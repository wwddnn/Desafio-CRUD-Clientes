package com.devsuperior.desafio_crud.controllers.handlers;

import com.devsuperior.desafio_crud.dto.CustomError;
import com.devsuperior.desafio_crud.dto.ValidationError;
import com.devsuperior.desafio_crud.services.exceptions.NoSuchElementException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
    public class ControllerExceptionHandler {

        //obs. NOT_FOUND 404
        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<CustomError> noSuchElement(NoSuchElementException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
            return ResponseEntity.status(status).body(err);
        }

    //obs. UNPROCESSABLE_ENTITY 422
    //CustomError is a supertype of ValidationError (is upcasting)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Erro de validação", request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}
