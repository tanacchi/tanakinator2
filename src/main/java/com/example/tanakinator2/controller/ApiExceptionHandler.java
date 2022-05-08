package com.example.tanakinator2.controller;

import com.example.tanakinator2.repository.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler
    private ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest request
    ) {
        logger.info("Resource not found. Send 404 response.");
        return super.handleExceptionInternal(
                e, null, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request
        );
    }
}
