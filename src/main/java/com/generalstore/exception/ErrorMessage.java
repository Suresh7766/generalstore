package com.generalstore.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class ErrorMessage {

    private int status;

    private String message;
}
