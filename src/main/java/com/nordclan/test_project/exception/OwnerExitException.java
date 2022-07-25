package com.nordclan.test_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, code = HttpStatus.CONFLICT)
public class OwnerExitException extends RuntimeException {

    public OwnerExitException() {
        super("Владелец не может покинуть свое событие");
    }
}
