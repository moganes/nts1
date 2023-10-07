package com.spectrum.notesapi.controller;

import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.model.api.response.accountInfo.AccountInfo;
import com.spectrum.notesapi.service.AccountInfoService;
import com.spectrum.notesapi.validator.AccountInfoRequestValidator;
import com.spectrum.notesapi.validator.DeviceInfoRequestValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author alam
 */
@RestController
@Slf4j
@Validated
@Api(value = "Account Info", description = "REST API for Account Info data", tags = {"Account Info"})
public class AccountInfoController {

    private final AccountInfoService accountInfoService;

    private final AccountInfoRequestValidator accountInfoRequestValidator;


    @Autowired
    public AccountInfoController(AccountInfoService accountInfoService,
                                 AccountInfoRequestValidator accountInfoRequestValidator) {
        this.accountInfoService = accountInfoService;
        this.accountInfoRequestValidator = accountInfoRequestValidator;
    }

    @ApiOperation(value = "Get Account number to account hash relation",
            produces = "application/json",
            response = AccountInfo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, request has been successfully processed"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "The server encountered an internal error or misconfiguration and was " +
                    "unable to complete your request", response = ErrorResponse.class)
    })

    @RequestMapping(value = "/accountToHashInfo",
            method = POST,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAccountNumberToHashInfo(
            @ApiParam(value = "Unique per request for tracing e.g efb30f92-d823-11ea-87d0-0242ac130003")
            @RequestHeader(value = "Tracing-Id") String tracingID,
            @ApiParam(value = "REST client app name teamName-projectName")
            @RequestHeader(value = "Client-App") String clientApp,
            @RequestBody List<String> billingAccountNumbers
    ) throws ExecutionException, InterruptedException {

        ResponseEntity<ErrorResponse> responseResponseEntity = accountInfoRequestValidator
                .validateAccountInfoRequest(billingAccountNumbers, tracingID, clientApp);

        if (responseResponseEntity != null) {
            log.info("Request validation failed, going to return response = {} , tracingID = {}, clientApp = {}",
                    responseResponseEntity, tracingID, clientApp);
            return responseResponseEntity;
        }

        return accountInfoService.getAccountNumberToHashInfo(billingAccountNumbers, clientApp, tracingID);
    }
}
