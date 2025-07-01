package com.milkFirst.MilkFirst_Complaint_Management_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ComplaintNotFoundException.class)
    public ResponseEntity<String> handleComplaintNotFound(ComplaintNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<String> handleStatus(MethodArgumentTypeMismatchException ex){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleStatus(){
        return new ResponseEntity<>("Mismatch Status .Please enter correct Status", HttpStatus.BAD_REQUEST);
    }
}
