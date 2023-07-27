package com.dans.rest.model.response;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final T data;

    private ApiResponse(ApiResponseBuilder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public static class ApiResponseBuilder<T> {
        private final int status;
        private final String message;
        private T data;

        public ApiResponseBuilder(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public ApiResponseBuilder<T> withData(T data) {
            this.data = data;

            return this;
        }

        public ResponseEntity<ApiResponse> build() {
            ApiResponse<T> apiResponse = new ApiResponse<>(this);

            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        }
    }
}
