package com.spectrum.notesapi.connector;

import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import com.spectrum.notesapi.model.spectrumSO.sdpAccount.request.GetSpcAccountDivisionRequest;
import com.spectrum.notesapi.model.spectrumSO.sdpAccount.request.QueryFields;
import com.spectrum.notesapi.model.spectrumSO.sdpAccount.response.GetSpcAccountDivisionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 03 Nov, 2020
 */
@Service
@Slf4j
public class SpectrumSOConnector {

    private final ExecutorService executorService;
    private final RestTemplate spectrumSoRestTemplate;

    private final String spcAccountDivisionEndpoint;


    public SpectrumSOConnector(ExecutorService executorService,
                               RestTemplate spectrumSoRestTemplate,
                               @Value("${app.spectrumSo.spcAccountDivision-url}") String spcAccountDivisionEndpoint) {
        this.executorService = executorService;
        this.spectrumSoRestTemplate = spectrumSoRestTemplate;
        this.spcAccountDivisionEndpoint = spcAccountDivisionEndpoint;
    }

    public Map<SearchCriteria, GetSpcAccountDivisionResponse> getSpcAccountDivisionInfo(List<SearchCriteria> sc, String clientApp, String tracingID) throws InterruptedException, ExecutionException {


        List<Callable<GetSpcAccountDivisionResponse>> tasks = sc.stream()
                .map(s -> (Callable<GetSpcAccountDivisionResponse>) () -> {

                    GetSpcAccountDivisionRequest spcAccountDivisionRequest = getSpectrumSORequest(s);

                    ResponseEntity<GetSpcAccountDivisionResponse> spectrumSOResponse = null;
                    try {
                        log.info("Going to call spectrum SO api = {}, request = {}, tracingID = {}, clientApp = {} ",
                                spcAccountDivisionEndpoint, spcAccountDivisionRequest, tracingID, clientApp);
                        spectrumSOResponse =
                                spectrumSoRestTemplate.postForEntity(spcAccountDivisionEndpoint, spcAccountDivisionRequest, GetSpcAccountDivisionResponse.class);

                        log.info("Got Spectrum SO response = {} , tracingID = {}, clientApp = {}",
                                spectrumSOResponse, tracingID, clientApp);
                    } catch (Exception e) {
                        log.error("Error while calling spectrum SO api tracingID = {}, clientApp = {} , exception = {}",
                                tracingID, clientApp, e);
                    }
                    //@TODO: handle all negative, e.g when validation error or account not found
                    GetSpcAccountDivisionResponse response = spectrumSOResponse.getBody();
                    response.setSc(s);
                    return response;
                })
                .collect(toList());

        List<Future<GetSpcAccountDivisionResponse>> futures = executorService.invokeAll(tasks);
        Map<SearchCriteria, GetSpcAccountDivisionResponse> resMap = new HashMap<>();

        try {
            GetSpcAccountDivisionResponse res = null;
            for (Future<GetSpcAccountDivisionResponse> future : futures) {
                res = future.get();
                resMap.put(res.getSc(), res);
            }
        } catch (Exception e) {
            log.error("Error while calling spectrum SO api tracingID = {}, clientApp = {} , exception = {}",
                    tracingID, clientApp, e);
            throw e;
        }

        return resMap;
    }

    private GetSpcAccountDivisionRequest getSpectrumSORequest(SearchCriteria sc) {

        GetSpcAccountDivisionRequest spcAccountDivisionRequest = null;
        if (!isEmpty(sc.getBillingAccountNumber())) {
            spcAccountDivisionRequest =
                    GetSpcAccountDivisionRequest.builder()
                            .getSpcAccountDivisionRequest(QueryFields.builder()
                                    .accountNumber(sc.getBillingAccountNumber())
                                    .build())
                            .build();
        } else if (!isEmpty(sc.getAccountFirstName())) {
            spcAccountDivisionRequest = GetSpcAccountDivisionRequest.builder()
                    .getSpcAccountDivisionRequest(QueryFields.builder()
                            .firstName(sc.getAccountFirstName())
                            .lastName(sc.getAccountLastName())
                            .zipCode5(sc.getZipCode5())
                            .build())
                    .build();
        } else if (!isEmpty(sc.getBusinessName())) {
            spcAccountDivisionRequest = GetSpcAccountDivisionRequest.builder()
                    .getSpcAccountDivisionRequest(QueryFields.builder()
                            .businessName(sc.getBusinessName())
                            .zipCode5(sc.getZipCode5())
                            .build())
                    .build();
        }
        return spcAccountDivisionRequest;

    }
}

