package com.spectrum.notesapi.validator;

import com.spectrum.notesapi.model.api.request.deviceInfo.DeviceInfoSearchCriteria;
import com.spectrum.notesapi.model.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 22 March, 2021
 */
@Service
@Slf4j
public class DeviceInfoRequestValidator {


    public ResponseEntity<ErrorResponse> validateGDeviceInfoRequest(
            List<DeviceInfoSearchCriteria> inputToValidate,
            String tracingID, String clientApp) throws ConstraintViolationException {

        StringBuilder sb = new StringBuilder();
        ResponseEntity<ErrorResponse> responseResponseEntity = null;


        //@TODO: configure the size, current is one will be more as per load testing/requirement
        if (inputToValidate.size() > 200) {
            responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(BAD_REQUEST.toString())
                    .errors(new ArrayList<String>() {{
                        add("Request list size must be less than 200. Found: " + inputToValidate.size());
                    }})
                    .build(), BAD_REQUEST);
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
                        .build(), BAD_REQUEST);
            }
        }


        return responseResponseEntity;
    }

    private String validateSearchCriteria(DeviceInfoSearchCriteria sc) {
        StringBuilder sb = null;

        if (sc.getBillingAccountNumber() == null && sc.getCmMac() == null) {
            sb = new StringBuilder().append("billingAccountNumber or cmMac one criteria should be passed. Search criteria: ")
                    .append(sc.toString());
        }

        else if (sc.getBillingAccountNumber() != null && sc.getCmMac() != null) {
            sb = new StringBuilder().append("billingAccountNumber or cmMac one criteria should be passed. Search criteria: ")
                    .append(sc.toString());
        }

        else if ((sc.getBillingAccountNumber() != null && sc.getBillingAccountNumber().trim().length() == 0)) {
            sb = new StringBuilder().append("billingAccountNumber value cannot be blank. Search criteria: ")
                    .append(sc.toString());
        }
        else if ((sc.getCmMac() != null && sc.getCmMac().trim().length() == 0)) {
            sb = new StringBuilder().append("cmMac value cannot be blank. Search criteria: ")
                    .append(sc.toString());
        }
        // Combination 1 --> billingAccountNumber
        else if (!isEmpty(sc.getBillingAccountNumber())
                && !(isEmpty(sc.getCmMac()))) {
            sb = new StringBuilder().append("Only one field per search criteria is applicable. Search criteria: ")
                    .append(sc.toString());
        }
        // Combination 2 --> (mac)
        else if (!isEmpty(sc.getCmMac())
                && !(isEmpty(sc.getBillingAccountNumber()))) {
            sb = new StringBuilder().append("If cmMac is present in a search criteria then other field " +
                    "must not be passed. Search criteria: ")
                    .append(sc.toString());
        }
        return sb != null ? sb.toString() : null;
    }
}

