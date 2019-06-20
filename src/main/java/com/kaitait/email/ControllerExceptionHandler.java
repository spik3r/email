package com.kaitait.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = MessagingException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public void MessagingExceptionErrorHandler(final HttpServletRequest req, final MessagingException e) {
        log.error("Can't send mail: " + req.getRequestURI(), e);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void RuntimeExceptionErrorHandler(final HttpServletRequest req, final RuntimeException e) {
        log.error("Something blew up: " + req.getRequestURI(), e);
    }
}
