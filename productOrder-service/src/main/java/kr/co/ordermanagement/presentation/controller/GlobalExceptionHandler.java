package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.exception.NotEnoughAmountException;
import kr.co.ordermanagement.presentation.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex){
        ErrorMessage errMsg = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotEnoughAmountException.class)
    public ResponseEntity<ErrorMessage> handleNotEnoughAmountException(NotEnoughAmountException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
