package com.nordclan.test_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, code = HttpStatus.CONFLICT)
public class IncorrectBookingPeriodException extends RuntimeException {

    public IncorrectBookingPeriodException() {
        super("Минимальный период бронирования - 30 минут");
    }
}
