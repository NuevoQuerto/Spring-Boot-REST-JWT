package com.dans.rest.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthenticationRequest {
    @NotEmpty
    private String username;

    @Length(min = 8, max = 32)
    private String password;
}
