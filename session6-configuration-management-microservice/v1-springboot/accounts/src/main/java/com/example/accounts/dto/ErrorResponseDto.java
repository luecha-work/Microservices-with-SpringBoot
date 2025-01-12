package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponseDto", description = "Data Transfer Object for Error Response")
@Data @AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "API path where the error occurred"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code of the error"
    )
    private HttpStatus httpStatus;

    @Schema(
            description = "Error message"
    )
    private String errorMessage;

    @Schema(
            description = "Time when the error occurred"
    )
    private LocalDateTime errorTime;
}
