package com.monov.cloud.gateway.response;

import com.monov.commons.dto.CourseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CourseResponseHandler {
    public static ResponseEntity<List<CourseDTO>> generateSuccessResponse(HttpStatus status,
                                                                          List<CourseDTO> response) {
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<String> generateErrorResponse(String message,HttpStatus status){
        return new ResponseEntity<String>(message,status);
    }
}
