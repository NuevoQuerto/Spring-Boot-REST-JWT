package com.dans.rest.controller;

import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse> all() {
        return jobService.getAll();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse> detail(@PathVariable String id) {
        return jobService.getById(id);
    }
}
