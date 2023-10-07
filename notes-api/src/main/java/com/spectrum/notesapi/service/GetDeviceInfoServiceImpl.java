package com.spectrum.notesapi.service;

import com.spectrum.notesapi.config.ElasticDBConfig;
import com.spectrum.notesapi.dao.ElasticSearchDAO;
import com.spectrum.notesapi.model.api.request.deviceInfo.DeviceInfoSearchCriteria;
import com.spectrum.notesapi.model.api.response.deviceInfo.*;
import com.spectrum.notesapi.model.db.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 27 Jul, 2020
 */
@Service
@Slf4j
public class GetDeviceInfoServiceImpl implements GetDeviceInfoService {

    private final ElasticsearchOperations elasticsearchOperations;
    private ElasticDBConfig elasticConfig;
    private final ExecutorService executorService;
    private final ElasticSearchDAO elasticSearchDAO;

    @Autowired
    public GetDeviceInfoServiceImpl(ElasticsearchOperations elasticsearchOperations,
                                    ElasticDBConfig elasticConfig,
                                    ExecutorService executorService,
                                    ElasticSearchDAO elasticSearchDAO) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.elasticConfig = elasticConfig;
        this.executorService = executorService;
        this.elasticSearchDAO = elasticSearchDAO;
    }

    @Override
    public ResponseEntity<?> getDeviceInfo(List<DeviceInfoSearchCriteria> request, String clientApp, String tracingID) throws ExecutionException, InterruptedException {
        ResponseEntity<?> responseEntity = null;

        final String apiName = "getFullDeviceInfoByMac";

        log.info("Going to process request = {} . clientApp = {} , tracingID = {}, request = {}", request, clientApp, tracingID, request);

        try {

            // Get CM data from ES
            List<CableModemDB> cableModemList = elasticSearchDAO.getCableModemInfoByBanMacList(request
                            .stream()
                            .filter(t -> !isEmpty(t.getBillingAccountNumber()))
                            .map(DeviceInfoSearchCriteria::getBillingAccountNumber)
                            .distinct()
                            .collect(toList()),
                    request
                            .stream()
                            .filter(t -> !isEmpty(t.getCmMac()))
                            .map(k -> k.getCmMac().toLowerCase().replace(":", ""))
                            .distinct()
                            .collect(toList()),
                    tracingID
            );

            if (cableModemList.size() == 0) {

                log.info("No cableModem records found to process tracingID = {}", tracingID);

                List<DeviceInfoResponse> deviceInfoResponseList = new ArrayList<>();
                request.forEach(sc -> {
                    deviceInfoResponseList.add(DeviceInfoResponse.builder()
                            .request(sc)
                            .build());
                });
                responseEntity = new ResponseEntity<>(
                        deviceInfoResponseList
                        , OK);
            } else {


                //Get full list of CM's by accountID's
                Future<List<CableModemDB>> fullCableModemListFuture = elasticSearchDAO.getCableModemInfoByAccountIdsAsync(cableModemList
                                .stream()
                                .filter(id -> !isEmpty(id.getAccountId()))
                                .map(CableModemDB::getAccountId)
                                .collect(toList()),
                        tracingID);

                // Get account data async
                Future<List<AccountDB>> accountListFuture = elasticSearchDAO.getAccountInfoByAccountIdsAsync(cableModemList
                                .stream()
                                .filter(id -> !isEmpty(id.getAccountId()))
                                .map(CableModemDB::getAccountId)
                                .collect(toList()),
                        tracingID);

                List<AccountDB> accountList = accountListFuture != null ? accountListFuture.get() : null;
                List<CableModemDB> fullCableModemList = fullCableModemListFuture != null ? fullCableModemListFuture.get() : new ArrayList<>();

                // Get macDomain Info
                Future<List<MacDomainDB>> macDomainDBFuture = elasticSearchDAO.getmacDomainByRecordIDsAsync(fullCableModemList
                                .stream()
                                .filter(id -> !isEmpty(id.getMacDomainId()))
                                .map(CableModemDB::getMacDomainId)
                                .collect(toList()),
                        tracingID);

                List<MacDomainDB> macDomainDBList = macDomainDBFuture != null ? macDomainDBFuture.get() : null;

                // handle if you got a device but account relation is missing
                if (fullCableModemList.size() == 0) {
                    fullCableModemList.addAll(cableModemList);
                }

                fullCableModemList.forEach(cm -> {
                    Optional<CableModemDB> optional = cableModemList.stream().filter(k -> k.getRecordID().equals(cm.getRecordID())).findFirst();
                    if (optional.isPresent() == false) {
                        cableModemList.add(cm);
                    }
                });

                // Get cmts data async
                Future<List<CmtsDB>> cmtsListFuture = elasticSearchDAO.getCMTSListByCMTSIdsAsync(fullCableModemList
                                .stream()
                                .filter(id -> !isEmpty(id.getCmts_id()))
                                .map(CableModemDB::getCmts_id)
                                .collect(toList()),
                        tracingID);

                List<CmtsDB> cmtsList = cmtsListFuture != null ? cmtsListFuture.get() : null;

                // Get region data async
                Future<List<RegionDB>> regionListFuture = (cmtsList != null && cmtsList.size() > 0)
                        ? elasticSearchDAO.getRegionListByRegionIdsAsync(cmtsList
                                .stream()
                                .filter(id -> !isEmpty(id.getRegionId()))
                                .map(CmtsDB::getRegionId)
                                .distinct()
                                .collect(toList()),
                        tracingID) : null;

                List<RegionDB> regionList = regionListFuture != null ? regionListFuture.get() : null;

                Future<List<HubDb>> hubListFuture = (cmtsList != null && cmtsList.size() > 0)
                        ? elasticSearchDAO.getHubListByHubIdsAsync(cmtsList
                                .stream()
                                .filter(id -> !isEmpty(id.getHubId()))
                                .map(CmtsDB::getHubId)
                                .distinct()
                                .collect(toList()),
                        tracingID) : null;

                List<HubDb> hubList = hubListFuture != null ? hubListFuture.get() : null;

                List<DeviceInfoResponse> deviceInfoResponses = request
                        .stream()
                        .map(req -> DeviceInfoResponse.builder()
                                .request(req)
                                .response(!isEmpty(req.getCmMac()) ?
                                        prepareDeviceInfoResponseForMac(req, cableModemList, accountList, cmtsList, regionList, hubList, macDomainDBList)
                                        : prepareDeviceInfoResponseForBan(req, cableModemList, accountList, cmtsList, regionList, hubList)
                                )
                                .build())
                        .collect(toList());

                responseEntity = new ResponseEntity<>(deviceInfoResponses, OK);

            }

        } catch (Exception e) {
            log.error("Error occurred while processing the request, tracingID={}, clientApp={}", tracingID, clientApp, e);
            throw e;
        }

        log.debug("Returning response for tracingID={}, clientApp={}, apiResponse={}", tracingID, clientApp, responseEntity);
        log.info("Returning response status = {} for tracingID={}, clientApp={}", responseEntity.getStatusCode(), tracingID, clientApp);

        return responseEntity;
    }

    private List<DeviceInfoFullByMACResponse> prepareDeviceInfoResponseForMac(DeviceInfoSearchCriteria req, List<CableModemDB> cableModemList,
                                                                              List<AccountDB> accountList, List<CmtsDB> cmtsList, List<RegionDB> regionList,
                                                                              List<HubDb> hubList,
                                                                              List<MacDomainDB> macDomainDBList) {

        List<DeviceInfoFullByMACResponse> deviceInfoFullByMACResponses = new ArrayList<>();

        CableModemDB reqCableModemDB = cableModemList.stream().filter(cm -> cm.getMac().equals(req.getCmMac().toLowerCase().replace(":", ""))).findFirst().orElse(null);

        if (reqCableModemDB == null)
            return null;

        AccountDB account = null;
        List<CableModemDB> cableModemsOnAccount = new ArrayList<>();
        cableModemsOnAccount.add(reqCableModemDB);
        CmtsDB cmts = null;

        if (reqCableModemDB != null) {

            if (accountList != null && accountList.size() > 0)
                account = accountList.stream().filter(ac -> ac.getRecordID().equals(reqCableModemDB.getAccountId())).findFirst().orElse(null);

            if (cableModemList != null && cableModemList.size() > 0) {
                cableModemList.forEach(cm -> {
                    // add only if account id is not blank, even target cm have blank accountID
                    if (cm.getAccountId().equals(reqCableModemDB.getAccountId()) && !isEmpty(cm.getAccountId()) && !reqCableModemDB.getMac().equals(cm.getMac()))
                        cableModemsOnAccount.add(cm);

                });
            }


            if (cmtsList != null && cmtsList.size() > 0) {
                Optional<CableModemDB> tempCm = cableModemList.stream().filter(cm -> !isEmpty(cm.getCmts_id()) && cm.getAccountId().equals(reqCableModemDB.getAccountId())).findFirst();
                if (tempCm.isPresent())
                    cmts = cmtsList.stream().filter(cmtsDB -> tempCm.get().getCmts_id().equals(cmtsDB.getRecordID())).findFirst().orElse(null);
            }
        }

        final CmtsDB finalCmts = cmts;

        HubDb hubDb = null;
        if (cmts != null && hubList != null && hubList.size() > 0)
            hubDb = hubList.stream().filter(hub -> hub.getRecordID().equals(finalCmts.getHubId())).findFirst().orElse(null);

        RegionDB regionDB = null;
        if (cmts != null && regionList != null && regionList.size() > 0) {

            regionDB = regionList.stream().filter(reg -> reg.getRecordID().equals(finalCmts.getRegionId())).findFirst().orElse(null);
        }


        deviceInfoFullByMACResponses.add(DeviceInfoFullByMACResponse.builder()
                .accountInfo(account != null ? AccountRes.builder()
                        .accountNumber(reqCableModemDB.getAccount_name())
                        .spcDivisionID(account.getSpcDivisionID())
                        .soaDivisionID(account.getSoaDivisionID())
                        .address(account != null ? Address.builder()
                                .street(account.getAddressStreet())
                                .apt(account.getAddressApt())
                                .city(account.getAddressCity())
                                .state(account.getAddressState())
                                .zip(account.getAddressZip())
                                .geoLocation(account.getCoordinatesLatitude() + "," + account.getCoordinatesLongitude())
                                .insertDateTime(account.getInsertDateTime())
                                .build() : null)
                        .build() : null)
                .accountEquipmentList(cableModemsOnAccount != null ? cableModemsOnAccount
                        .stream()
                        .map(cm -> AccountEquipment.builder()
                                .insertDateTime(cm.getInsertDateTime())
                                .ip(cm.getIp())
                                .mac(cm.getMac())
                                .equipmentType(cm.getEquipmentType())
                                .model(cm.getModel())
                                .make(cm.getMake())
                                .build())
                        .collect(toList()) : null)
                .cmts(
                        cmts != null ?
                                CMTSRes.builder()
                                        .hardwareVersion(cmts.getHwVersion())
                                        .insertDateTime(cmts.getInsertDateTime())
                                        .ip(cmts.getIp())
                                        .ipv4(cmts.getIpv4())
                                        .ipv6(cmts.getIpv6())
                                        .make(cmts.getMake())
                                        .sysDescr(cmts.getSysDescr())
                                        .sysLoc(cmts.getSysLoc())
                                        .poller(cmts.getPoller())
                                        .managementAreaName(cmts.getMgtAreaName())
                                        .marketName(cmts.getMarketName())
                                        .model(cmts.getModel())
                                        .name(cmts.getName())
                                        .regionName(cmts.getRegionName())
                                        .serial(cmts.getSerial())
                                        .softwareVersion(cmts.getSwVersion())
                                        //.vendor(cmts.getDocsis_vendor())
                                        //.hub_name(cableModems.get(0).getHub_name())
                                        .macDomain(MacdomainRes.builder()
                                                .name(macDomainDBList != null ? macDomainDBList.stream()
                                                        .filter(macDomainDB -> reqCableModemDB.getMacDomainId().equals(macDomainDB.getRecordID()))
                                                        .map(MacDomainDB::getName)
                                                        .findFirst()
                                                        .orElse(null) : null
                                                )
                                                .node(NodeRes.builder()
                                                        .name(reqCableModemDB.getNodeName())
                                                        .build())
                                                .build())
                                        .hubName((hubDb != null) ? hubDb.getHubName() : null)
                                        .build() : null)
                .region(
                        regionDB != null ?
                                RegionRes.builder()
                                        .comBasicVideoSub(regionDB.getComBasicVideoSub())
                                        .comHSDSub(regionDB.getComHSDSub())
                                        .comVoiceSub(regionDB.getComVoiceSub())
                                        .enabled(regionDB.getEnabled())
                                        .insertDateTime(regionDB.getInsertDateTime())
                                        .latitude(regionDB.getCoordinates_latitude())
                                        .longitude(regionDB.getCoordinates_longitude())
                                        .name(regionDB.getName())
                                        .remedyName(regionDB.getRemedy_name())
                                        .resBasicVideoSub(regionDB.getResBasicVideoSub())
                                        .resHSDSub(regionDB.getResHSDSub())
                                        .resVoiceSub(regionDB.getResVoiceSub())
                                        .build() : null)
                .build());

        return deviceInfoFullByMACResponses.size() > 0 ? deviceInfoFullByMACResponses : null;
    }

    private List<DeviceInfoFullByMACResponse> prepareDeviceInfoResponseForBan(DeviceInfoSearchCriteria req, List<CableModemDB> cableModemList,
                                                                              List<AccountDB> accountList, List<CmtsDB> cmtsList, List<RegionDB> regionList,
                                                                              List<HubDb> hubList) {

        List<DeviceInfoFullByMACResponse> deviceInfoFullByMACResponses = new ArrayList<>();

        List<AccountDB> accountDBList = accountList.stream()
                .filter(ac -> ac.getAccount().equals(req.getBillingAccountNumber()))
                .collect(toList());

        if (accountDBList.size() == 0)
            return null;


        accountDBList.forEach(accountDB -> {

            List<CableModemDB> cableModemsOnAccount = new ArrayList<>();
            CmtsDB cmts = null;

            // Get cm on account
            if (cableModemList != null && cableModemList.size() > 0) {
                cableModemList.forEach(cm -> {
                    // add only if account id is not blank, even target cm have blank accountID
                    if (cm.getAccountId().equals(accountDB.getRecordID()))
                        cableModemsOnAccount.add(cm);

                });
            }

            // get the cmts serving the account
            if (cmtsList != null && cmtsList.size() > 0) {
                Optional<CableModemDB> tempCm = cableModemList.stream()
                        .filter(cm -> !isEmpty(cm.getCmts_id()) && cm.getAccountId().equals(accountDB.getRecordID()))
                        .findFirst();
                if (tempCm.isPresent())
                    cmts = cmtsList.stream()
                            .filter(cmtsDB -> tempCm.get().getCmts_id().equals(cmtsDB.getRecordID()))
                            .findFirst()
                            .orElse(null);
            }

            final CmtsDB finalCmts = cmts;

            HubDb hubDb = null;
            if (cmts != null && hubList != null && hubList.size() > 0)
                hubDb = hubList.stream()
                        .filter(hub -> hub.getRecordID().equals(finalCmts.getHubId())).findFirst()
                        .orElse(null);

            RegionDB regionDB = null;
            if (cmts != null && regionList != null && regionList.size() > 0) {

                regionDB = regionList.stream()
                        .filter(reg -> reg.getRecordID().equals(finalCmts.getRegionId()))
                        .findFirst()
                        .orElse(null);
            }

            deviceInfoFullByMACResponses.add(DeviceInfoFullByMACResponse.builder()
                    .accountInfo(AccountRes.builder()
                            .accountNumber(accountDB.getAccount())
                            .spcDivisionID(accountDB.getSpcDivisionID())
                            .soaDivisionID(accountDB.getSoaDivisionID())
                            .address(Address.builder()
                                    .street(accountDB.getAddressStreet())
                                    .apt(accountDB.getAddressApt())
                                    .city(accountDB.getAddressCity())
                                    .state(accountDB.getAddressState())
                                    .zip(accountDB.getAddressZip())
                                    .geoLocation(accountDB.getCoordinatesLatitude() + "," + accountDB.getCoordinatesLongitude())
                                    .insertDateTime(accountDB.getInsertDateTime())
                                    .build())
                            .build())
                    .accountEquipmentList(cableModemsOnAccount
                            .stream()
                            .map(cm -> AccountEquipment.builder()
                                    .insertDateTime(cm.getInsertDateTime())
                                    .ip(cm.getIp())
                                    .mac(cm.getMac())
                                    .equipmentType(cm.getEquipmentType())
                                    .model(cm.getModel())
                                    .make(cm.getMake())
                                    .build())
                            .collect(toList()))
                    .cmts(
                            cmts != null ?
                                    CMTSRes.builder()
                                            .hardwareVersion(cmts.getHwVersion())
                                            .insertDateTime(cmts.getInsertDateTime())
                                            .ip(cmts.getIp())
                                            .ipv4(cmts.getIpv4())
                                            .ipv6(cmts.getIpv6())
                                            .make(cmts.getMake())
                                            .sysDescr(cmts.getSysDescr())
                                            .sysLoc(cmts.getSysLoc())
                                            .poller(cmts.getPoller())
                                            .managementAreaName(cmts.getMgtAreaName())
                                            .marketName(cmts.getMarketName())
                                            .model(cmts.getModel())
                                            .name(cmts.getName())
                                            .regionName(cmts.getRegionName())
                                            .serial(cmts.getSerial())
                                            .softwareVersion(cmts.getSwVersion())
                                            //.vendor(cmts.getDocsis_vendor())
                                            //.hub_name(cableModems.get(0).getHub_name())
                                            .hubName((hubDb != null) ? hubDb.getHubName() : null)
                                            .build() : null)
                    .region(
                            regionDB != null ?
                                    RegionRes.builder()
                                            .comBasicVideoSub(regionDB.getComBasicVideoSub())
                                            .comHSDSub(regionDB.getComHSDSub())
                                            .comVoiceSub(regionDB.getComVoiceSub())
                                            .enabled(regionDB.getEnabled())
                                            .insertDateTime(regionDB.getInsertDateTime())
                                            .latitude(regionDB.getCoordinates_latitude())
                                            .longitude(regionDB.getCoordinates_longitude())
                                            .name(regionDB.getName())
                                            .remedyName(regionDB.getRemedy_name())
                                            .resBasicVideoSub(regionDB.getResBasicVideoSub())
                                            .resHSDSub(regionDB.getResHSDSub())
                                            .resVoiceSub(regionDB.getResVoiceSub())
                                            .build() : null)
                    .build());
        });

        return deviceInfoFullByMACResponses.size() > 0 ? deviceInfoFullByMACResponses : null;
    }
}
