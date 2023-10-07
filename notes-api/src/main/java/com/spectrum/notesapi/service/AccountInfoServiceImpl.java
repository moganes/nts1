package com.spectrum.notesapi.service;

import com.spectrum.notesapi.config.ElasticDBConfig;
import com.spectrum.notesapi.dao.ElasticSearchDAO;
import com.spectrum.notesapi.model.api.response.accountInfo.AccountInfo;
import com.spectrum.notesapi.model.db.AccountDB;
import com.spectrum.notesapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by "Mohammad Shah Alam" on 30 Nov, 2021
 */
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    private final ElasticsearchOperations elasticsearchOperations;
    private ElasticDBConfig elasticConfig;
    private final ElasticSearchDAO elasticSearchDAO;

    @Autowired
    public AccountInfoServiceImpl(ElasticsearchOperations elasticsearchOperations,
                                  ElasticDBConfig elasticConfig,
                                  ElasticSearchDAO elasticSearchDAO) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.elasticConfig = elasticConfig;
        this.elasticSearchDAO = elasticSearchDAO;
    }

    @Override
    public ResponseEntity<?> getAccountNumberToHashInfo(List<String> billingAccountNumbers, String clientApp, String tracingID) throws ExecutionException, InterruptedException {

        ResponseEntity<?> responseEntity = null;

        final String apiName = "getAccountNumberToHashInfo";

        log.debug("Going to process request = {} . clientApp = {} , tracingID = {}",
                billingAccountNumbers, clientApp, tracingID);
        log.info("Going to process billingAccountNumbers = {} . clientApp = {} , tracingID = {}",
                billingAccountNumbers.size(), clientApp, tracingID);

        try {
            List<AccountDB> accountList = elasticSearchDAO.getAccountInfoByBillingAccountNumberList(billingAccountNumbers
                            .stream()
                            .distinct()
                            .collect(toList()),
                    tracingID);

            List<AccountInfo> responseList = billingAccountNumbers
                    .stream()
                    .distinct()
                    .map(reqBan -> {
                        AccountDB rec = accountList.
                                stream()
                                .filter(t -> reqBan.equals(t.getAccount()))
                                .findFirst().orElse(null);

                        if (rec != null) {
                            return AccountInfo.builder()
                                    .accountHashId(rec.getRecordID())
                                    .billingAccountNumber(reqBan)
                                    .build();
                        } else {
                            return AccountInfo.builder()
                                    .accountHashId(null)
                                    .billingAccountNumber(reqBan)
                                    .build();
                        }

                    })
                    .collect(toList());


            responseEntity = new ResponseEntity<>(responseList,
                    CommonUtils.getHttpHeaders(tracingID, clientApp),
                    OK);


        } catch (Exception e) {
            log.error("Error occurred while processing the request, tracingID={}, clientApp={}", tracingID, clientApp, e);
            throw e;
        }

        log.debug("Returning response for tracingID={}, clientApp={}, apiResponse={}", tracingID, clientApp, responseEntity);
        log.info("Returning response status = {} for tracingID={}, clientApp={}", responseEntity.getStatusCode(), tracingID, clientApp);

        return responseEntity;
    }
}
