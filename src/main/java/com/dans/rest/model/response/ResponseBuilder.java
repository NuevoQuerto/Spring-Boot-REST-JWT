package com.dans.rest.model.response;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
    public ResponseEntity<ApiResponse> buildResponse(int status, String message) {
        return new ApiResponse.ApiResponseBuilder<>(status, message).build();
    }

    public ResponseEntity<ApiResponse> buildResponse(int status, String message, Object data) {
        return new ApiResponse.ApiResponseBuilder<>(status, message).withData(data).build();
    }
}
