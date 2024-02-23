package com.spring.security.springsecuritybasic.advice;

import com.spring.security.springsecuritybasic.dto.ResponseDto;
import com.spring.security.springsecuritybasic.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto EntityNotFoundExceptionHandler(EntityNotFoundException exception){
        return new ResponseDto(404,"Error",exception.getMessage());
    }

}
