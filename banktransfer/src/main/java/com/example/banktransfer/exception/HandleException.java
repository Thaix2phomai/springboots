package com.example.banktransfer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class HandleException {
    // Xử lý NotFound
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(ChangeSetPersister.NotFoundException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Xử BadRequest
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorMessage> handleTransferException(TransferException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WithDrawException.class)
    public ResponseEntity<ErrorMessage> handleWithDrawException(WithDrawException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Xử lý các Exception còn lại
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleOtherException(RuntimeException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
