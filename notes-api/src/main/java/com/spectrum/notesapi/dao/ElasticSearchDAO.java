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
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 16 Oct, 2020
 */
@Service
@Slf4j
public class ElasticSearchDAO {

    //@TODO: Logging
    private final ElasticsearchOperations elasticsearchOperations;
    private final ExecutorService executorService;
    private final ElasticDBConfig elasticDBConfig;

    @Autowired
    public ElasticSearchDAO(ElasticsearchOperations elasticsearchOperations,
                            ExecutorService executorService,
                            ElasticDBConfig elasticDBConfig) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.executorService = executorService;
        this.elasticDBConfig = elasticDBConfig;
    }


    public List<CableModemDB> getCableModemInfoByAccountID(List<String> billingAccountRecordIDs, String tracingID) {

        log.debug("Going to get cable modem data from NOTES DB for billingAccountRecordIDCount = {}, tracingID = {}",
                billingAccountRecordIDs.size(), tracingID);

        log.info("Going to get cable modem data from NOTES DB for billingAccountRecordIDCount = {}, tracingID = {}",
                billingAccountRecordIDs.size(), tracingID);


        List<CableModemDB> cableModemList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("account_id", billingAccountRecordIDs))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, CableModemDB.class).forEach(sh -> cableModemList.add(sh.getContent()));

        log.debug("Response from NOTES DB by billingAccountRecordIDs = {}, cableModemList ={} tracingID = {}", billingAccountRecordIDs, cableModemList, tracingID);
        log.info("Response from NOTES DB  by billingAccountRecordIDsCount = {}, cableModemListCount ={} tracingID = {}", billingAccountRecordIDs.size(), cableModemList.size(), tracingID);

        return cableModemList;
    }

    public List<MacDomainDB> getMacDomainInfoByIds(List<String> macDomainRecordIDs, String tracingID) {

        log.debug("Going to get MacDomain data from NOTES DB for macDomainRecordIDCount = {}, tracingID = {}",
                macDomainRecordIDs.size(), tracingID);

        log.info("Going to get MacDomain data from NOTES DB for macDomainRecordIDCount = {}, tracingID = {}",
                macDomainRecordIDs.size(), tracingID);


        List<MacDomainDB> macDomainDBList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("recordID", macDomainRecordIDs))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, MacDomainDB.class).forEach(sh -> macDomainDBList.add(sh.getContent()));

        log.debug("Response from NOTES DB by billingAccountRecordIDs = {}, cableModemList ={} tracingID = {}",
                macDomainRecordIDs, macDomainDBList, tracingID);

        log.info("Response from NOTES DB  by macDomainRecordIDsCount = {}, cableModemListCount ={} tracingID = {}",
                macDomainRecordIDs.size(), macDomainDBList.size(), tracingID);

        return macDomainDBList;
    }


    public Future<List<CableModemDB>> getCableModemInfoByAccountIdsAsync(List<String> billingAccountRecordIDs, String tracingID) {

        Future<List<CableModemDB>> cableModemListFuture = null;
        if (billingAccountRecordIDs.stream().anyMatch(id -> !isEmpty(id))) {
            cableModemListFuture = executorService.submit(() -> getCableModemInfoByAccountID(billingAccountRecordIDs
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.info("Skipping CableModem DB call as no non null/blank billingAccountRecordIDs found in billingAccountRecordIDsCount={},  tracingID={} ",
                    billingAccountRecordIDs.size(), tracingID);
        }
        return cableModemListFuture;
    }

    public Future<List<MacDomainDB>> getmacDomainByRecordIDsAsync(List<String> macDomainRecordIDs, String tracingID) {

        Future<List<MacDomainDB>> macDomainListFuture = null;
        if (macDomainRecordIDs.stream().anyMatch(id -> !isEmpty(id))) {
            macDomainListFuture = executorService.submit(() -> getMacDomainInfoByIds(macDomainRecordIDs
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.info("Skipping CableModem DB call as no non null/blank billingAccountRecordIDs found in billingAccountRecordIDsCount={},  tracingID={} ",
                    macDomainRecordIDs.size(), tracingID);
        }
        return macDomainListFuture;
    }

    public List<CableModemDB> getCableModemInfoByBanMacList(List<String> billingAccountNumbers, List<String> macs, String tracingID) {

        log.info("Going to get cable modem data from NOTES DB for billingAccountNumbersCount = {}, macsCount = {} tracingID = {}",
                billingAccountNumbers.size(), macs.size(), tracingID);

        log.debug("Going to get cable modem data from NOTES DB for billingAccountNumbers = {}, macs = {} tracingID = {}",
                billingAccountNumbers, macs, tracingID);


        List<CableModemDB> cableModemList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .should(termsQuery("account_name", billingAccountNumbers))
                                .should(termsQuery("mac", macs))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, CableModemDB.class).forEach(sh -> cableModemList.add(sh.getContent()));

        log.debug("Response from NOTES DB , cableModemList ={} tracingID = {}", cableModemList, tracingID);
        log.info("Response from NOTES DB , cableModemListCount ={} tracingID = {}", cableModemList.size(), tracingID);

        return cableModemList;
    }

    public List<AccountDB> getAccountInfoByBillingAccountNumberList(List<String> billingAccountNums, String tracingID) {

        log.info("Going to get account data from NOTES DB for billingAccountNumsCount = {} tracingID = {}",
                billingAccountNums.size(), tracingID);

        log.debug("Going to get account data from NOTES DB for billingAccountNums = {} tracingID = {}",
                billingAccountNums, tracingID);


        List<AccountDB> accountDBList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("account", billingAccountNums))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, AccountDB.class).forEach(sh -> accountDBList.add(sh.getContent()));

        log.info("Response from NOTES DB , accountDBListCount ={} tracingID = {}", accountDBList.size(), tracingID);
        log.debug("Response from NOTES DB , accountDBList ={} tracingID = {}", accountDBList, tracingID);

        return accountDBList;
    }

    public List<AccountDB> getAccountInfoByBanList(List<String> billingAccountIds, String tracingID) {

        log.info("Going to get account data from NOTES DB for billingAccountIdsCount = {} tracingID = {}",
                billingAccountIds.size(), tracingID);

        log.debug("Going to get account data from NOTES DB for billingAccountIds = {} tracingID = {}",
                billingAccountIds, tracingID);


        List<AccountDB> accountDBList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("recordID", billingAccountIds))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, AccountDB.class).forEach(sh -> accountDBList.add(sh.getContent()));

        log.info("Response from NOTES DB , accountDBListCount ={} tracingID = {}", accountDBList.size(), tracingID);
        log.debug("Response from NOTES DB , accountDBList ={} tracingID = {}", accountDBList, tracingID);

        return accountDBList;
    }

    public Future<List<AccountDB>> getAccountInfoByAccountIdsAsync(List<String> billingAccountIds, String tracingID) {

        Future<List<AccountDB>> accountListFuture = null;
        if (billingAccountIds.stream().anyMatch(id -> !isEmpty(id))) {
            accountListFuture = executorService.submit(() -> getAccountInfoByBanList(billingAccountIds
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.info("Skipping Account DB call as no non null/blank billingAccountIds id's found in billingAccountIdsCount={},  tracingID={} ",
                    billingAccountIds.size(), tracingID);
        }
        return accountListFuture;
    }

    public Future<List<CmtsDB>> getCMTSListByCMTSIdsAsync(List<String> cmtsIds, String tracingID) {

        Future<List<CmtsDB>> cmtsListFuture = null;
        if (cmtsIds.stream().anyMatch(cmtsId -> !isEmpty(cmtsId))) {
            cmtsListFuture = executorService.submit(() -> getCMTSListByCMTSIds(cmtsIds
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.info("Skipping cmts DB call as no non null/blank cmts id's found in cmtsIdsCount={},  tracingID={} ",
                    cmtsIds.size(), tracingID);
            log.debug("Skipping cmts DB call as no non null/blank cmts id's found in cmtsIds={},  tracingID={} ",
                    cmtsIds, tracingID);
        }
        return cmtsListFuture;
    }

    public Future<List<HubDb>> getHubListByHubIdsAsync(List<String> hubIds, String tracingID) {

        Future<List<HubDb>> hubFuture = null;
        if (hubIds.stream().anyMatch(hubId -> !isEmpty(hubId))) {
            hubFuture = executorService.submit(() -> getHubListByHubIds(hubIds
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.info("Skipping hub DB call as no non null/blank hubIds found in hubIdsCount={},  tracingID={} ",
                    hubIds.size(), tracingID);

            log.debug("Skipping hub DB call as no non null/blank hubIds found in hubIds={},  tracingID={} ",
                    hubIds, tracingID);
        }
        return hubFuture;
    }


    public List<HubDb> getHubListByHubIds(List<String> hubIds, String tracingID) {

        log.debug("Going to get hub data from NOTES DB for hubIds = {} tracingID = {}", hubIds, tracingID);
        log.info("Going to get hub data from NOTES DB for hubIdsCount = {} tracingID = {}", hubIds.size(), tracingID);


        List<HubDb> hubList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("recordID", hubIds))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, HubDb.class).forEach(sh -> hubList.add(sh.getContent()));

        log.debug("Response from NOTES DB , hubList ={} tracingID = {}", hubList, tracingID);
        log.info("Response from NOTES DB , hubListCount ={} tracingID = {}", hubList.size(), tracingID);

        return hubList;
    }


    public Future<List<RegionDB>> getRegionListByRegionIdsAsync(List<String> regionIds, String tracingID) {

        Future<List<RegionDB>> regionFuture = null;
        if (regionIds.stream().anyMatch(regionId -> !isEmpty(regionId))) {
            regionFuture = executorService.submit(() -> getRegionListByRegionIds(regionIds
                            .stream().distinct().filter(id -> !isEmpty(id)).collect(toList()),
                    tracingID));
        } else {
            log.debug("Skipping region DB call as no non null/blank regionIds found in regionIds={},  tracingID={} ",
                    regionIds, tracingID);

            log.info("Skipping region DB call as no non null/blank regionIds found in regionIdsCount={},  tracingID={} ",
                    regionIds.size(), tracingID);
        }
        return regionFuture;
    }

    public List<RegionDB> getRegionListByRegionIds(List<String> regionIds, String tracingID) {

        log.debug("Going to get region data from NOTES DB for regionIds = {} tracingID = {}", regionIds, tracingID);
        log.info("Going to get region data from NOTES DB for regionIdsCount = {} tracingID = {}", regionIds.size(), tracingID);


        List<RegionDB> regionList = new ArrayList<>();
        Query sq = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termsQuery("recordID", regionIds))
                )
                .withFilter(boolQuery()
                        .must(rangeQuery("insertDateTime")
                                .lte(elasticDBConfig.getInsertedTimeLessThan())
                                .gte(elasticDBConfig.getInsertedTimeGreaterThan())
                        ))
                .build();

        elasticsearchOperations.search(sq, RegionDB.class).forEach(sh -> regionList.add(sh.getContent()));

        log.debug("Response from NOTES DB , regionList ={} tracingID = {}", regionList, tracingID);
        log.info("Response from NOTES DB , regionListCount ={} tracingID = {}", regionList.size(), tracingID);

        return regionList;
    }

    public List<CmtsDB> getCMTSListByCMTSIds(List<String> cmtsIds, String tracingID) {

        log.debug("Going to get cmts data from NOTES DB for cmtsIds = {} tracingID = {}", cmtsIds, tracingID);
        log.info("Going to get cmts data from NOTES DB for cmtsIdsCount = {} tracingID = {}", cmtsIds.size(), tracingID);


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

        log.debug("Response from NOTES DB , cmtsList ={} tracingID = {}", cmtsList, tracingID);
        log.info("Response from NOTES DB , cmtsListCount ={} tracingID = {}", cmtsList.size(), tracingID);

        return cmtsList;
    }
}
