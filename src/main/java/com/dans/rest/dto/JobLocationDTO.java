package com.dans.rest.dto;

import com.dans.rest.model.response.Job;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JobLocationDTO {
    private String location;
    private List<Job> data;
}
