package com.rest.employee.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DaoException extends Exception {

    private HttpStatus httpStatus;

    public DaoException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
