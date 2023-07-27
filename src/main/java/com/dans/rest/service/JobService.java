package com.dans.rest.service;

import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.model.response.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private ResponseBuilder responseBuilder;

    public JobService(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse> getAll() {
        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Jobs"
        );
    }
}
