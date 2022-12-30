package com.fullcreative.phonebook.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fullcreative.phonebook.customresponse.ErrorMessage;




@ControllerAdvice
@ResponseStatus
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorMessage> contactNotFoundException(ContactNotFoundException exception,WebRequest request){
          ErrorMessage message =new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());

          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorMessage> EmailExistException(EmailExistsException exception,WebRequest request){
          ErrorMessage message =new ErrorMessage(HttpStatus.ALREADY_REPORTED,exception.getMessage());
System.err.println(message.getStatus());
          return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(message);
    }


}
