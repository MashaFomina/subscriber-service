package com.subscriber.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;

import com.subscriber.utils.ResponseHelper;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(Exception ex) {
        return ResponseHelper.getStatusResponseWithBody(
                ((ResponseStatusException) ex).getStatus(),
                ((ResponseStatusException) ex).getReason());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return ResponseHelper.getNotFoundResponseWithBody(ex.getMessage());
    }

    @ExceptionHandler({
            ValidationException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<String> handleValidationException(Exception ex) {
        return ResponseHelper.getStatusResponseWithBody(
                HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<String> handleConversionException(Exception ex) {
        return ResponseHelper.getStatusResponseWithBody(
                HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseHelper.
                getStatusResponseWithBody(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage());
    }
}
