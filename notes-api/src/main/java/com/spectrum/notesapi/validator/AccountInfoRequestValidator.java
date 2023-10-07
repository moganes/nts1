package com.spectrum.notesapi.validator;

import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by "Mohammad Shah Alam" on 07 Jan, 2022
 */
@Service
@Slf4j
public class AccountInfoRequestValidator {

    public ResponseEntity<ErrorResponse> validateAccountInfoRequest(
            List<String> billingAccountNumbers,
            String tracingID, String clientApp) throws ConstraintViolationException {

        ResponseEntity<ErrorResponse> responseResponseEntity = null;

        //@TODO: configure the size, current is one will be more as per load testing/requirement
        if (billingAccountNumbers.size() > 100) {
            responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(BAD_REQUEST.toString())
                    .errors(new ArrayList<String>() {{
                        add("Request list size must be less than 100. Found: " + billingAccountNumbers.size());
                    }})
                    .build(), CommonUtils.getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
        } else {

            Optional<Boolean> result = billingAccountNumbers
                    .stream()
                    .map(ObjectUtils::isEmpty)
                    .findFirst();

            if (!result.isPresent()) {
                responseResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                        .timestamp(new Date().toString())
                        .status(BAD_REQUEST.toString())
                        .errors(new ArrayList<String>() {{
                            add("Billing account number cannot be null or blank");
                        }})
                        .build(), CommonUtils.getHttpHeaders(tracingID, clientApp), BAD_REQUEST);
            }
        }
        return responseResponseEntity;
    }
}
