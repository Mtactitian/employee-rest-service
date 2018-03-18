package com.rest.employee.exception;

import lombok.Getter;

@Getter
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }
}
