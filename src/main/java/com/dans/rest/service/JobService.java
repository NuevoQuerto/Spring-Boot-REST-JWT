package com.dans.rest.service;

import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.model.response.Job;
import com.dans.rest.dto.JobLocationDTO;
import com.dans.rest.model.response.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobService {
    private ResponseBuilder responseBuilder;
    private RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URI = "http://dev3.dansmultipro.co.id/api/recruitment/positions";

    public JobService(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse> getAll() {
        List<JobLocationDTO> jobLocations = new ArrayList<>();

        // With Data Transfer Object
        Job[] response = restTemplate.getForObject(String.format("%s.json", BASE_URI), Job[].class);
        Arrays.stream(response)
                .collect(Collectors.groupingBy(Job::getLocation))
                .forEach((location, jobs) -> {
                    jobLocations.add(new JobLocationDTO(location, jobs));
                });

        // Without Data Transfer Object
//        List<HashMap> response = restTemplate.getForObject(String.format("%s.json", BASE_URI), List.class);
//        response.stream()
//                .collect(Collectors.groupingBy(job -> (String) job.get("location")))
//                .forEach((location, jobs) -> {
//                    jobLocations.add(
//                            new JobLocation(
//                                    location,
//                                    jobs.stream().map(job -> new Job(
//                                            (String) job.get("id"),
//                                            (String) job.get("type"),
//                                            (String) job.get("url"),
//                                            (Date) job.get("createdAt"),
//                                            (String) job.get("company"),
//                                            (String) job.get("companyUrl"),
//                                            (String) job.get("location"),
//                                            (String) job.get("title"),
//                                            (String) job.get("description"),
//                                            (String) job.get("howToApply"),
//                                            (String) job.get("companyLogo")
//                                    )).collect(Collectors.toList())
//                            )
//                    );
//                });

        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Success Get Job List",
                Map.of("result", jobLocations)
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
