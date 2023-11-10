package com.epam.jmp.service.controllers.advice;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ErrorResponse {

    private final String message;

    public ErrorResponse(Exception ex) {
        this.message = ExceptionUtils.getRootCause(ex).getMessage();
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
