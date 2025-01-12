package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDto", description = "Data Transfer Object for Customer")
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "test@exemple.com"
    )
    @NotEmpty(message = "Email address cannot be empty")
    @Email(message = "Email address is not valid")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
