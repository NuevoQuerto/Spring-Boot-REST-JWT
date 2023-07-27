package com.dans.rest.service;

import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.model.response.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobService {
    private ResponseBuilder responseBuilder;
    private RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URI = "http://dev3.dansmultipro.co.id/api/recruitment/positions";

    public JobService(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse> getAll() {
        Object response = restTemplate.getForObject(String.format("%s.json", BASE_URI), Object.class);

        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Success Get Job List",
                response
        );
    }

    public ResponseEntity<ApiResponse> getById(String id) {
        Object response = restTemplate.getForObject(String.format("%s/%s", BASE_URI, id), Object.class);

        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Success Get Job Detail",
                response
        );
    }
}
