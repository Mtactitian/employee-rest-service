package com.rest.employee.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
    private HttpStatus httpStatus;

    public ServiceException(DaoException exception) {
        super(exception.getMessage(), exception.getCause());
        this.httpStatus = exception.getHttpStatus();
    }
}
