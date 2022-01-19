package com.monov.cloud.gateway.exception.handler;

import com.monov.cloud.gateway.exception.ItemNotFoundException;
import com.monov.cloud.gateway.response.StudentResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentCourseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleNoItemFoundException() {
        return StudentResponseHandler.generateErrorResponse("No results found", HttpStatus.OK);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleMethodNotAllowedException(HttpClientErrorException ex) {
        return StudentResponseHandler.generateErrorResponse( ex.getResponseBodyAsString(),
                ex.getStatusCode());
    }

}
