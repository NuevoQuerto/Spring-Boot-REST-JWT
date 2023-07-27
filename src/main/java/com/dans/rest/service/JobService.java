package com.dans.rest.service;

import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.model.response.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class JobService {
    private ResponseBuilder responseBuilder;
    private RestTemplate restTemplate = new RestTemplate();

    public JobService(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse> getAll() {
        Object response = restTemplate.getForObject("http://dev3.dansmultipro.co.id/api/recruitment/positions.json", Object.class);

        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Success Get Job List",
                response
        );
    }
}
