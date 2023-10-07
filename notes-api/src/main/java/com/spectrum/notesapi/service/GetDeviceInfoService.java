package com.spectrum.notesapi.service;

import com.spectrum.notesapi.model.api.request.deviceInfo.DeviceInfoSearchCriteria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by "Mohammad Shah Alam" on 27 Jul, 2020
 */
public interface GetDeviceInfoService {
    // ResponseEntity<?> getFullDeviceInfoByBAN(String billingAccountNumber, String clientApp, String tracingID) throws ExecutionException, InterruptedException;
     ResponseEntity<?> getDeviceInfo(List<DeviceInfoSearchCriteria> request, String clientApp, String tracingID) throws ExecutionException, InterruptedException;
}
