package com.nordclan.test_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, code = HttpStatus.CONFLICT)
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Редактировать событие может только его организатор");
    }
}
