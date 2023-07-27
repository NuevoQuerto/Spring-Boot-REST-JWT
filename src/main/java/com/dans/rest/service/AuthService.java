package com.dans.rest.service;

import com.dans.rest.model.request.AuthenticationRequest;
import com.dans.rest.model.response.ApiResponse;
import com.dans.rest.model.response.ResponseBuilder;
import com.dans.rest.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;
    private ResponseBuilder responseBuilder;

    public AuthService(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, ResponseBuilder responseBuilder) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<ApiResponse> authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            return responseBuilder.buildResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Incorrect username or password."
            );
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = JwtUtil.generateToken(userDetails);

        return responseBuilder.buildResponse(
                HttpStatus.OK.value(),
                "Login Success.",
                jwt
        );
    }
}
