package com.monov.cloud.gateway.response;

import com.monov.commons.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GatewayResponseHandler {
    public static ResponseEntity<StudentDTO> generateSuccessResponse(HttpStatus status,
                                                                         StudentDTO response) {
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<String> generateErrorResponse(String message,HttpStatus status){
        return new ResponseEntity<String>(message,status);
    }
}
