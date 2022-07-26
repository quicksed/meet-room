package com.nordclan.test_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, code = HttpStatus.CONFLICT)
public class AlreadyInBookingException extends RuntimeException {

    public AlreadyInBookingException() {
        super("Пользователь уже принимает участие в событии");
    }
}
