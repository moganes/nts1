package com.spectrum.notesapi.validator;

import com.spectrum.notesapi.model.api.request.ripRoute.SMBStaticIpRipInforRequest;
import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.spectrum.notesapi.utils.CommonUtils.getHttpHeaders;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 29 Oct, 2020
 */

//@TODO: move validation messages to config
@Service
@Slf4j
public class RipRouteInfoRequestValidator {

    public ResponseEntity<ErrorResponse> validateSMBStaticIPRequest(List<SMBStaticIpRipInforRequest> requests, String tracingID, String clientApp) {
        //@TODO: For now its one request at a time only, implementation is done to take list and return list.
        ResponseEntity<ErrorResponse> responseResponseEntity = null;
        if (requests.size() != 1) {
            responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(BAD_REQUEST.toString())
                    .errors(new ArrayList<String>() {{
                        add("Request list size must be one. Found: " + requests.size());
                    }})
                    .build(), getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
        }


        List<String> res = requests.stream()
                .map(this::validateSmbIPRequest)
                .filter(c -> !isEmpty(c))
                .collect(toList());
        if (!isEmpty(res)) {
            responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(BAD_REQUEST.toString())
                    .errors(res)
                    .build(), getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
        }
        return responseResponseEntity;
    }

    public ResponseEntity<ErrorResponse> validateGetRipInfoRequest(
            List<SearchCriteria> inputToValidate,
            String tracingID, String clientApp) throws ConstraintViolationException {

        StringBuilder sb = new StringBuilder();
        ResponseEntity<ErrorResponse> responseResponseEntity = null;


        //@TODO: configure the size, current is one will be more as per load testing/requirement
        if (inputToValidate.size() != 1) {
            responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(BAD_REQUEST.toString())
                    .errors(new ArrayList<String>() {{
                        add("Request list size must be one. Found: " + inputToValidate.size());
                    }})
                    .build(), getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
        } else {
            List<String> res = inputToValidate.stream()
                    .map(this::validateSearchCriteria)
                    .filter(c -> !isEmpty(c))
                    .collect(toList());
            if (!isEmpty(res)) {
                responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                        .timestamp(new Date().toString())
                        .status(BAD_REQUEST.toString())
                        .errors(res)
                        .build(), getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
            }
        }


        return responseResponseEntity;
    }

    private String validateSmbIPRequest(SMBStaticIpRipInforRequest sc) {

        StringBuilder sb = new StringBuilder();
        if (isEmpty(sc) || isEmpty(sc.getStaticIP()) || isEmpty(sc.getStaticIP().trim())) {
            sb.append("Static IPv4 request cannot be blank/null/empty " + sc.toString());
        }
        if (!(isEmpty(sc) || isEmpty(sc.getStaticIP()) || isEmpty(sc.getStaticIP().trim()))
                && !CommonUtils.isValidIPV4(sc.getStaticIP())
        ) {
            sb.append(" Invalid Static IPv4 " + sc.toString());
        }
        return sb.toString();
    }

    private String validateSearchCriteria(SearchCriteria sc) {

        StringBuilder sb = null;
        // Combination 1 --> billingAccountNumber
        if (!isEmpty(sc.getBillingAccountNumber())
                && !(isEmpty(sc.getAccountFirstName())
                || isEmpty(sc.getAccountLastName())
                || isEmpty(sc.getBusinessName())
                || isEmpty(sc.getZipCode5()))) {
            sb = new StringBuilder().append("If billingAccountNumber is present then other field must not be passed. Search criteria: ")
                    .append(sc.toString());
        }
        // Combination 2 --> (accountFirstName + accountLastName + zipCode5), Residential customer
        else if (isEmpty(sc.getBillingAccountNumber())
                && (
                !isEmpty(sc.getAccountFirstName())
                        || !isEmpty(sc.getAccountLastName()))
                && (isEmpty(sc.getAccountFirstName())
                || isEmpty(sc.getAccountLastName())
                || isEmpty(sc.getZipCode5())
                || !isEmpty(sc.getBusinessName()))) {
            sb = new StringBuilder().append("If accountLastName OR accountFirstName is supplied then combination must be " +
                    "(accountFirstName + accountLastName + zipCode5)" +
                    " and other fields must not be supplied. Search criteria: ")
                    .append(sc.toString());
        }
        // Combination 3 --> (businessName + zipCode5), Commercial customer
        else if (isEmpty(sc.getBillingAccountNumber())
                && isEmpty(sc.getAccountFirstName())
                && isEmpty(sc.getAccountLastName())
                && (isEmpty(sc.getBusinessName()) || isEmpty(sc.getZipCode5()))

        ) {
            sb = new StringBuilder().append(" if businessName is present then combination must be " +
                    "(businessName + zipCode5) and other fields must not be supplied. Search criteria: ")
                    .append(sc.toString());
        }
        return sb != null ? sb.toString() : null;
    }
}

