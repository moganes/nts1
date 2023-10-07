package com.spectrum.notesapi.dao;

import com.spectrum.notesapi.config.ElasticDBConfig;
import com.spectrum.notesapi.model.db.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by "Mohammad Shah Alam" on 16 Oct, 2020
 */
@Service
@Slf4j
public class RipRouteDAO {

    //@TODO: Logging
    private final ElasticsearchOperations elasticsearchOperations;
    private final ExecutorService executorService;
    private final ElasticDBConfig elasticDBConfig;

    @Autowired
    public RipRouteDAO(ElasticsearchOperations elasticsearchOperations,
                       ExecutorService executorService,
                       ElasticDBConfig elasticDBConfig) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.executorService = executorService;
        this.elasticDBConfig = elasticDBConfig;
    }

    public List<RipRouteCableModem> getRipDataByStaticIps(List<String> staticIps, String tracingID) {

        log.info("Going to get rip route data from NOTES DB for staticIps = {}, tracingID = {}", staticIps, tracingID);

        List<RipRouteCableModem> ripRouteCableModems = new ArrayList<>();


            Query sq = new NativeSearchQueryBuilder()
                    .withQuery(
                            boolQuery()
                                    .must(
                                            termsQuery("network", staticIps)
                                    )
                    )
                    .withFilter(boolQuery()
                            .must(rangeQuery("insertDateTime")
                                    .lte(elasticDBConfig.getSmbInsertedTimeLessThan())
                                    .gte(elasticDBConfig.getSmbInsertedTimeGreaterThan())
                            ))
                    .build();

            elasticsearchOperations.search(sq, RipRouteCableModem.class).forEach(sh -> ripRouteCableModems.add(sh.getContent()));

        log.info("Response from NOTES DB for CM, cableModems ={} tracingID = {}", ripRouteCableModems, tracingID);

        return ripRouteCableModems;
    }


    public List<AccountDB> getAccountInfoByBAN(List<String> billingAccountNumbers, String tracingID) {

        log.info("Going to get account data from NOTES DB for billingAccountNumbers = {}, tracingID = {}", billingAccountNumbers, tracingID);


        List<AccountDB> accounts = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(
                                        termsQuery("account", billingAccountNumbers)
                                )
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, AccountDB.class).forEach(sh -> accounts.add(sh.getContent()));

        log.info("Response from NOTES DB , accounts ={} tracingID = {}", accounts, tracingID);

        return accounts;
    }

    public List<RipPasswordDB> getRipPasswordInfoByClli(List<String> clliList, String tracingID) {

        log.info("Going to get rip password data from NOTES DB for clliList = {}, tracingID = {}", clliList, tracingID);


        List<RipPasswordDB> ripPasswordDBList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(
                                        termsQuery("clli", clliList)
                                )
                )
                .build();

        elasticsearchOperations.search(sq, RipPasswordDB.class).forEach(sh -> ripPasswordDBList.add(sh.getContent()));
        //@TODO mask rip password in logging.
        log.info("Response from NOTES DB , ripPasswordDBList ={} tracingID = {}", ripPasswordDBList, tracingID);

        return ripPasswordDBList;
    }

    public List<CableModemDB> getCableModemsByAccountRecordIds(List<String> accountRecordIds, String tracingID) {

        log.info("Going to get cablemodem data from NOTES DB for accountRecordIds = {}, tracingID = {}", accountRecordIds, tracingID);

        List<CableModemDB> cableModems = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(
                                        termsQuery("account_id", accountRecordIds)
                                )
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, CableModemDB.class).forEach(sh -> cableModems.add(sh.getContent()));

        log.info("Response from NOTES DB for CM, cableModems ={} tracingID = {}", cableModems, tracingID);

        return cableModems;
    }

    public List<CmtsDB> getCMTSListByCMTSId(List<String> cmtsIds, String tracingID) {
        List<CmtsDB> cmtsList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("recordID", cmtsIds))
                                .must(termsQuery("enabled", "1"))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, CmtsDB.class).forEach(sh -> cmtsList.add(sh.getContent()));

        return cmtsList;
    }
}
