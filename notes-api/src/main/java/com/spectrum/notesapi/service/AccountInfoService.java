package com.spectrum.notesapi.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by "Mohammad Shah Alam" on 30 Nov, 2021
 */
public interface AccountInfoService {
    ResponseEntity<?> getAccountNumberToHashInfo(
            List<String> billingAccountNumbers, String clientApp, String tracingID)
            throws ExecutionException, InterruptedException;
}
