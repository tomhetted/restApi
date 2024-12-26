package ru.smirnovjavadev.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MyException.class)
    public ErrorResponse handleInternalException(MyException e) {
        var response = ErrorResponse.build(
                HttpStatus.NOT_FOUND, e.getMessage());
        log.error("Internal Server Error: {}", response);
        if (log.isTraceEnabled()) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return response;
    }

}