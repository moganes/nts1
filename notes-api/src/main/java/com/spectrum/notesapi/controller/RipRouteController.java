 package com.spectrum.notesapi.controller;

import com.spectrum.notesapi.model.api.request.ripRoute.SMBStaticIpRipInforRequest;
import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.model.api.response.ripRoute.RipRouteResponse;
import com.spectrum.notesapi.model.api.response.ripRoute.SMBStaticIpRipInfoResponse;
import com.spectrum.notesapi.service.RipRouteService;
import com.spectrum.notesapi.validator.RipRouteInfoRequestValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@RestController
@Slf4j
@Api(value = "RIP Route", description = "REST API for RIP Route data", tags = {"RIP Route"})
@SwaggerDefinition(consumes = "application/json", produces = "application/json", schemes = {SwaggerDefinition.Scheme.HTTPS})
@Validated
public class RipRouteController {

    private RipRouteService ripRouteService;
    private RipRouteInfoRequestValidator ripRouteInfoRequestValidator;

    @Autowired
    public RipRouteController(RipRouteService ripRouteService, RipRouteInfoRequestValidator ripRouteInfoRequestValidator) {
        this.ripRouteService = ripRouteService;
        this.ripRouteInfoRequestValidator = ripRouteInfoRequestValidator;
    }

   /* @InitBinder
    public void initMerchantOnlyBinder(WebDataBinder binder) {
        binder.addValidators(ripRouteInfoRequestValidator);
    }*/

    @ApiOperation(value = "Get RIP Route information",
            notes = "<u>Implementation Notes</u> </br></br> Request Rip Route data by below combinations</br>" +
                    "Combination 1 --> billingAccountNumber\n" +
                    // "Combination 2 --> (accountFirstName + accountLastName + zipCode5), Residential customer\n" +
                    "Combination 2 --> (businessName + zipCode5), Commercial customer" +
                    "",
            response = RipRouteResponse.class, responseContainer = "List", tags = {"RIP Route"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, request has been successfully processed"),
            @ApiResponse(code = 204, message = "The server successfully processed the request, but no record was found", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "The server encountered an internal error or misconfiguration and was unable to complete your request", response = ErrorResponse.class),
    })
    @RequestMapping(value = "/ripRoute",
            method = POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRipRouteInfo(
            @ApiParam(value = "Unique per request for tracing e.g efb30f92-d823-11ea-87d0-0242ac130003", required = true)
            @RequestHeader(value = "Tracing-Id", required = true) String tracingID,

            @ApiParam(value = "REST client app name <teamName>-<projectName> e.g IP-Control-processName", required = true)
            @RequestHeader(value = "Client-App", required = true) String clientApp,
            //@MaxSizeConstraint
            @Valid
            @RequestBody List<SearchCriteria> request
    ) {

        ResponseEntity<ErrorResponse> responseResponseEntity = ripRouteInfoRequestValidator.validateGetRipInfoRequest(request, tracingID, clientApp);

        if (responseResponseEntity != null) {
            log.info("Request validation failed, going to return response = {} , tracingID = {}, clientApp = {}",
                    responseResponseEntity, tracingID, clientApp);
            return responseResponseEntity;
        }

        return ripRouteService.getRipRouteInfo(request, tracingID, clientApp);
    }

    @ApiOperation(value = "Get RIP Route information by static IP of SMB customers",
            notes = "<u>Implementation Notes</u> </br></br> Request Rip Route data by by static IP</br>",
            response = SMBStaticIpRipInfoResponse.class, responseContainer = "List", tags = {"RIP Route"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, request has been successfully processed"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "The server encountered an internal error or misconfiguration and was unable to complete your request", response = ErrorResponse.class),
    })
    @RequestMapping(value = "/ripRoute/smb",
            method = POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRipRouteInfoByStaticIP(
            @ApiParam(value = "Unique per request for tracing e.g efb30f92-d823-11ea-87d0-0242ac130003", required = true)
            @RequestHeader(value = "Tracing-Id", required = true) String tracingID,

            @ApiParam(value = "REST client app name <teamName>-<projectName> e.g IP-Control-processName", required = true)
            @RequestHeader(value = "Client-App", required = true) String clientApp,
            @Valid
            @RequestBody List<SMBStaticIpRipInforRequest> requests
    ) {

        ResponseEntity<ErrorResponse> responseResponseEntity = ripRouteInfoRequestValidator
                .validateSMBStaticIPRequest(requests, tracingID, clientApp);

        if (responseResponseEntity != null) {
            log.info("Request validation failed, going to return response = {} , tracingID = {}, clientApp = {}",
                    responseResponseEntity, tracingID, clientApp);
            return responseResponseEntity;
        }

        return ripRouteService.getRipRouteInfoByStaticIp(requests, tracingID, clientApp);
    }
}
