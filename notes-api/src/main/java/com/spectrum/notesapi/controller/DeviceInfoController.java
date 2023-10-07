package com.spectrum.notesapi.controller;

import com.spectrum.notesapi.model.api.request.deviceInfo.DeviceInfoSearchCriteria;
import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.model.api.response.deviceInfo.DeviceInfoResponse;
import com.spectrum.notesapi.service.GetDeviceInfoService;
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
@Api(value = "Device Info", description = "REST API for Device Info data", tags = {"Device Info"})
public class DeviceInfoController {

    private final GetDeviceInfoService deviceInfoService;

    private final DeviceInfoRequestValidator deviceInfoRequestValidator;


    @Autowired
    public DeviceInfoController(GetDeviceInfoService deviceInfoService,
                                DeviceInfoRequestValidator deviceInfoRequestValidator) {
        this.deviceInfoService = deviceInfoService;
        this.deviceInfoRequestValidator = deviceInfoRequestValidator;
    }
/*
    @ApiOperation(value = "Get complete device/equipment information by Billing Account Number",
            produces = "application/json",
            response = DeviceInfoFullByBANResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, request has been successfully processed"),
            @ApiResponse(code = 404, message = "The server successfully processed the request, but no record was found in the last 24 hours", response = Message.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "The server encountered an internal error or misconfiguration and was unable to complete your request", response = ErrorResponse.class)
    })

    @RequestMapping(value = "/deviceInfo/full/ban/{billingAccountNumber}",
            method = GET,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getFullDeviceInfoByBAN(
            @ApiParam(value = "Unique per request for tracing e.g efb30f92-d823-11ea-87d0-0242ac130003")
            @RequestHeader(value = "Tracing-Id") String tracingID,
            @ApiParam(value = "REST client app name <teamName>-<projectName> e.g Wifi-deviceJob")
            @RequestHeader(value = "Client-App") String clientApp,
            @PathVariable(value = "billingAccountNumber") @NotNull @NotEmpty String billingAccountNumber
    ) throws ExecutionException, InterruptedException {
        return deviceInfoService.getFullDeviceInfoByBAN(billingAccountNumber, clientApp, tracingID);
    }
*/

    @ApiOperation(value = "Get complete device/equipment information",
            notes = "<u>Implementation Notes</u> </br></br> Request device info data by below query combinations</br>" +
                    "Combination 1 --> cable modem mac\n" +
                    "Combination 2 --> Billing account number",
            produces = "application/json",
            response = DeviceInfoResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, request has been successfully processed"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "The server encountered an internal error or misconfiguration and was " +
                    "unable to complete your request", response = ErrorResponse.class)
    })

    @RequestMapping(value = "/deviceInfo",
            method = POST,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getDeviceInfo(
            @ApiParam(value = "Unique per request for tracing e.g efb30f92-d823-11ea-87d0-0242ac130003")
            @RequestHeader(value = "Tracing-Id") String tracingID,

            @ApiParam(value = "REST client app name <teamName>-<projectName>")
            @RequestHeader(value = "Client-App") String clientApp,
            @RequestBody List<DeviceInfoSearchCriteria> request
    ) throws ExecutionException, InterruptedException {

        ResponseEntity<ErrorResponse> responseResponseEntity = deviceInfoRequestValidator
                .validateGDeviceInfoRequest(request, tracingID, clientApp);

        if (responseResponseEntity != null) {
            log.info("Request validation failed, going to return response = {} , tracingID = {}, clientApp = {}",
                    responseResponseEntity, tracingID, clientApp);
            return responseResponseEntity;
        }
        return deviceInfoService.getDeviceInfo(request, clientApp, tracingID);
    }
}
