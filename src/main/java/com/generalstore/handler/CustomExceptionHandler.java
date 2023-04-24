package com.generalstore.handler;

import com.generalstore.exception.ErrorMessage;
import com.generalstore.exception.GeneralStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GeneralStoreException.class)
    public ResponseEntity<ErrorMessage> handleGeneralStoreException(Throwable t) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(t.getMessage());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleGeneralStoreException(MethodArgumentNotValidException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(e.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(" | ")));
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
