package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.dto.ResponseDto;
import com.example.accounts.sevice_contract.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Account",
        description = "CRUD REST API for Accounts in Workshop EazyBank")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService accountsService;

//    @Value("${build.version}")
//    private final String buildVersion;

    @Operation(
            summary = "Create Account REST API",
            description = "Create a new account for a customer")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    @PostMapping("/account")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccounts(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Get Account Detail REST API",
            description = "Get account details for a customer")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found"
            )
    })
    @GetMapping("/account")
    public ResponseEntity<CustomerDto> getAccountDetail(@RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber) {
        CustomerDto customerDto = accountsService.getAccountDetailByMobilePhone(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "Update account details for a customer")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PatchMapping("/account")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        HttpStatus status = isUpdated ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = isUpdated ? AccountsConstants.MESSAGE_200 : AccountsConstants.MESSAGE_500;
        String statusCode = isUpdated ? AccountsConstants.STATUS_200 : AccountsConstants.STATUS_500;

        return ResponseEntity.status(status).body(new ResponseDto(statusCode, message));
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "Delete account for a customer")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/account")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        HttpStatus status = isDeleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = isDeleted ? AccountsConstants.MESSAGE_200 : AccountsConstants.MESSAGE_500;
        String statusCode = isDeleted ? AccountsConstants.STATUS_200 : AccountsConstants.STATUS_500;

        return ResponseEntity.status(status).body(new ResponseDto(statusCode, message));
    }

//    @GetMapping("/build-info")
//    public ResponseEntity<String> getBuildInfo() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(buildVersion);
//    }
}