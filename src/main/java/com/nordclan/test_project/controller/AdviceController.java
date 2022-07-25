package com.nordclan.test_project.controller;

import com.nordclan.test_project.exception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(value = {AlreadyInBookingException.class, IncorrectBookingPeriodException.class, PeriodAlreadyBookedException.class})
    public String handleConflictExceptions(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleIllegalArgumentException(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public String handleAccessDeniedException(HttpServletRequest request, Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "errors/error";
    }
}
