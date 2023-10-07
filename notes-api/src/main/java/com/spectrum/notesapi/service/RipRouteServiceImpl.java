package com.spectrum.notesapi.service;

import com.spectrum.notesapi.connector.SpectrumSOConnector;
import com.spectrum.notesapi.dao.RipRouteDAO;
import com.spectrum.notesapi.model.api.request.ripRoute.SMBStaticIpRipInforRequest;
import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import com.spectrum.notesapi.model.api.response.ErrorResponse;
import com.spectrum.notesapi.model.api.response.ripRoute.*;
import com.spectrum.notesapi.model.db.AccountDB;
import com.spectrum.notesapi.model.db.CableModemDB;
import com.spectrum.notesapi.model.db.CmtsDB;
import com.spectrum.notesapi.model.db.RipRouteCableModem;
import com.spectrum.notesapi.model.spectrumSO.sdpAccount.response.GetSpcAccountDivisionResponse;
import com.spectrum.notesapi.model.spectrumSO.sdpAccount.response.SpcAccountDivisionList;
import com.spectrum.notesapi.utils.CommonUtils;
import com.spectrum.notesapi.utils.IpAddressMatcherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.spectrum.notesapi.utils.CommonUtils.getCustomerType;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by "Mohammad Shah Alam" on 15 Oct, 2020
 */
@Service
@Slf4j
public class RipRouteServiceImpl implements RipRouteService {

    private RipRouteDAO ripRouteDAO;
    private final SpectrumSOConnector soConnector;


    @Autowired
    public RipRouteServiceImpl(RipRouteDAO ripRouteDAO, SpectrumSOConnector soConnector) {
        this.ripRouteDAO = ripRouteDAO;
        this.soConnector = soConnector;
    }


    @Override
    public ResponseEntity<?> getRipRouteInfo(List<SearchCriteria> searchCriteriaList, String tracingID, String clientApp) {

        log.info("Going to process request = {} . clientApp = {} , tracingID = {}", searchCriteriaList, clientApp, tracingID);

        ResponseEntity<?> response;
        // Call spectrum SO api to get details.
        Map<SearchCriteria, GetSpcAccountDivisionResponse> soResponses;
        try {
            soResponses = soConnector.getSpcAccountDivisionInfo(searchCriteriaList, clientApp, tracingID);
        } catch (Exception e) {

            log.error("Error while calling spectrum SO api tracingID = {}, clientApp = {} , exception = {}",
                    tracingID, clientApp, e);

            response = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(INTERNAL_SERVER_ERROR.toString())
                    .errors(new ArrayList<String>() {{
                        add("Error occurred while invoking SO API " + e.getMessage());
                    }})
                    .build(), getHttpHeaders(tracingID), INTERNAL_SERVER_ERROR);

            log.info("Going to return response = {} . clientApp = {} , tracingID = {}", response, clientApp, tracingID);


            return response;

        }

        if (soResponses.size() == 0) {

            log.info("No record found in SO response, clientApp = {} , tracingID = {}", clientApp, tracingID);

            response = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(NO_CONTENT.toString())
                    .errors(new ArrayList<String>() {{
                        add("No data found in SO for given request");
                    }})
                    .build(), getHttpHeaders(tracingID), NO_CONTENT);

            log.info("Going to return response = {} . clientApp = {} , tracingID = {}", response, clientApp, tracingID);

            return response;

        }


        List<String> banList = new ArrayList<>();


        Map<SearchCriteria, GetSpcAccountDivisionResponse> finalSoResponses = soResponses;

        // Collect ban to query telemetry data
        searchCriteriaList.forEach(sc -> {

            GetSpcAccountDivisionResponse res = finalSoResponses.get(sc);
            List<SpcAccountDivisionList> spcAccountDivisionList = null;
            if (res != null
                    && res.getGetSpcAccountDivisionResponse() != null
                    && res.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList() != null
                    && res.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().size() > 0) {
                spcAccountDivisionList = res.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList();
            }

            if (!isEmpty(sc.getBillingAccountNumber())) {
                banList.add(sc.getBillingAccountNumber());
            } else if (spcAccountDivisionList != null) {
                spcAccountDivisionList.forEach(k -> {
                    banList.add(k.getAccountNumber().startsWith("0") ? removeZeroPad(k.getAccountNumber()) : k.getAccountNumber());
                    if (k.getAccountNumber().startsWith("0"))
                        k.setAccountNumber(removeZeroPad(k.getAccountNumber()));
                });
            }
        });


        if (banList.size() == 0) {
            response = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(NO_CONTENT.toString())
                    .errors(new ArrayList<String>() {{
                        add("Unable to locate billing account number for given request");
                    }})
                    .build(), getHttpHeaders(tracingID), OK);

            log.info("Going to return response = {} . clientApp = {} , tracingID = {}", response, clientApp, tracingID);

            return response;

        }

        List<AccountDB> accountList = ripRouteDAO.getAccountInfoByBAN(banList.stream().distinct().collect(toList()), tracingID);

        if (accountList.size() == 0) {
            response = new ResponseEntity<>(ErrorResponse.builder()
                    .timestamp(new Date().toString())
                    .status(NO_CONTENT.toString())
                    .errors(new ArrayList<String>() {{
                        add("Unable to locate billing account data for given request");
                    }})
                    .build(), getHttpHeaders(tracingID), OK);

            log.info("Going to return response = {} . clientApp = {} , tracingID = {}", response, clientApp, tracingID);

            return response;
        }

        List<CableModemDB> cableModemDataList = ripRouteDAO.getCableModemsByAccountRecordIds(accountList.stream().map(AccountDB::getRecordID).collect(Collectors.toList()), tracingID);

        //TODO: remove duplicate cmts id with network present check
        List<String> cmtsIds = (cableModemDataList != null && cableModemDataList.size() > 0) ? cableModemDataList
                .stream()
                .map(CableModemDB::getCmts_id)
                .distinct()
                .collect(toList()) : null;

        // TODO: handle no cmts data found

        List<CmtsDB> cmtsList = (cmtsIds != null && cmtsIds.size() > 0) ? ripRouteDAO.getCMTSListByCMTSId(cmtsIds, tracingID) : null;

        if (cmtsList != null && cmtsList.size() > 0) {
            List<String> clliList = cmtsList
                    .stream()
                    .filter(cmtsDB -> !isEmpty(cmtsDB.getName()))
                    .map(cmtsDB -> getClli(cmtsDB.getName()))
                    .distinct()
                    .collect(toList());

            if (clliList != null && clliList.size() > 0) {
                //List<RipPasswordDB> ripPasswordDBList = ripRouteDAO.getRipPasswordInfoByClli(clliList, tracingID);

            }
        }

        // Prepare the response
        List<RipRouteResponse> res = searchCriteriaList.stream().map(sc -> RipRouteResponse.builder()
                .request(sc)
                .response(prepareRipInfoList(sc, accountList, cmtsList, cableModemDataList, finalSoResponses))
                .build())
                .collect(toList());

        return new ResponseEntity<>(res, OK);
    }

    @Override
    public ResponseEntity<?> getRipRouteInfoByStaticIp(List<SMBStaticIpRipInforRequest> staticIpList, String tracingID, String clientApp) {

        log.info("Going to process request = {} . clientApp = {} , tracingID = {}", staticIpList, clientApp, tracingID);

        ResponseEntity<?> response = null;


        try {
            List<RipRouteCableModem> ripRouteCableModemList = ripRouteDAO.getRipDataByStaticIps(staticIpList
                    .stream()
                    .map(SMBStaticIpRipInforRequest::getStaticIP)
                    .collect(toList()), tracingID);

            List<SMBStaticIpRipInfoResponse> smbStaticIpRipInfoResponses = new ArrayList<>();

            staticIpList.forEach(sc -> {
                SMBStaticIpRipInfoResponse res = new SMBStaticIpRipInfoResponse();
                res.setRequest(sc);
                smbStaticIpRipInfoResponses.add(res);

                ripRouteCableModemList.forEach(r -> {
                    if (matches(sc.getStaticIP(), r.getNetwork())) {
                        if (res.getResponse() == null)
                            res.setResponse(new ArrayList<>());

                        res.getResponse().add(ResponseInfo.builder()
                                .mac(r.getMac())
                                .network(r.getNetwork())
                                .enterpriseName(r.getEnterprise_name())
                                .account(SmbAccount.builder()
                                        .soaDivisionID(r.getSoaDivisionID())
                                        .accountNumber(r.getAccount_name())
                                        .spcDivisionID(r.getSpcDivisionID())
                                        .build())
                                .build());
                    }
                });
            });

            response = new ResponseEntity<>(smbStaticIpRipInfoResponses,
                    CommonUtils.getHttpHeaders(tracingID, clientApp),
                    OK);

            String status = "NO_DATA";
            if (smbStaticIpRipInfoResponses != null
                    && smbStaticIpRipInfoResponses.get(0) != null
                    && smbStaticIpRipInfoResponses.get(0).getResponse() != null
                    && smbStaticIpRipInfoResponses.get(0).getResponse().size() > 0) {
                status = "SUCCESS";
            }


            log.info("ResponseEntity /ripRoute/smb , status = {} {}", status, response);


        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    private boolean matches(String ip, String subnet) {
        IpAddressMatcherUtil ipAddressMatcher = new IpAddressMatcherUtil(subnet);
        return ipAddressMatcher.matches(ip);
    }

    private String getClli(String fqdn) {
        return fqdn.substring(5, fqdn.indexOf('.'));
    }

    private String removeZeroPad(String accountNumber) {
        String str = null;
        try {

            str = String.valueOf(Integer.parseInt(accountNumber));

        } catch (NumberFormatException e) {
            log.error("String does not contain a valid number = {}", accountNumber);
        }
        return str;
    }

    private HttpHeaders getHttpHeaders(String tracingID) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Tracing-Id", tracingID);
        return responseHeaders;
    }

    private String getNetwork(List<CableModemDB> cableModemDataList) {
        CableModemDB cableModem = cableModemDataList.stream()
                .filter(cm -> (cm.getNetwork() != null && cm.getNetwork().trim().length() > 0))
                .findFirst().orElse(null);
        return cableModem != null ? cableModem.getNetwork() : null;
    }

    private List<RipRouteInfo> prepareRipInfoList(SearchCriteria sc, List<AccountDB> accountList, List<CmtsDB> cmtsList, List<CableModemDB> cableModemDataList, Map<SearchCriteria, GetSpcAccountDivisionResponse> finalSoResponses) {

        List<RipRouteInfo> ripRouteInfos;

        // Get the account list based on search criteria
        GetSpcAccountDivisionResponse soRes = finalSoResponses.get(sc);

        List<AccountDB> targetList = accountList.stream().filter(k -> {
            boolean result = false;
            if (!isEmpty(sc.getBillingAccountNumber()) && sc.getBillingAccountNumber().equals(k.getAccount()))
                result = true;
            else if (!isEmpty(sc.getAccountFirstName()) || !isEmpty(sc.getBusinessName())) {
                if (soRes != null
                        && soRes.getGetSpcAccountDivisionResponse() != null
                        && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList() != null
                        && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().size() > 0
                        && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().stream().anyMatch(t -> t.getAccountNumber().equals(k.getAccount()))
                ) {
                    result = true;
                }
            }
            return result;

        }).collect(toList());


        // combination where search is by BAN
        //  if (!isEmpty(sc.getBillingAccountNumber()) && accountList.stream().anyMatch(a -> a.getAccount().equals(sc.getBillingAccountNumber()))) {

        ripRouteInfos = targetList.stream()
                .map(ac -> RipRouteInfo.builder()
                        .cmts(getCmtsData(ac, cableModemDataList, cmtsList))
                        .ripRoute(getRipData(cableModemDataList, ac))
                        .account(Account.builder()
                                .customerType(getCustomerType(ac.getCustomerType()))
                                .billingID(isEmpty(ac.getBillingID()) ? getBillingIDFromSoResponse(soRes, ac) : ac.getBillingID())
                                .accountNumber(ac.getAccount())
                                .name(getAccountHolderName(soRes, ac))
                                .address(Address.builder()
                                        .zip(ac.getAddressZip())
                                        .street(ac.getAddressStreet())
                                        .state(ac.getAddressState())
                                        .longitudes(ac.getCoordinatesLongitude())
                                        .latitude(ac.getCoordinatesLatitude())
                                        .city(ac.getAddressCity())
                                        .apt(ac.getAddressApt())
                                        .build())
                                .build())
                        .build())
                .collect(toList());

        //   }

        return ripRouteInfos;
    }

    private CMTS getCmtsData(AccountDB ac, List<CableModemDB> cableModemDataList, List<CmtsDB> cmtsList) {

        if (cmtsList == null || cmtsList.size() == 0)
            return null;

        CMTS cmts = null;

        for (CableModemDB cableModemDB :
                cableModemDataList) {

            if (ac.getRecordID().equals(cableModemDB.getAccountId())) {
                CmtsDB cmtsDB = cmtsList.stream().filter(k -> k.getRecordID().equals(cableModemDB.getCmts_id())).findFirst().orElse(null);
                if (cmtsDB != null) {
                    cmts = CMTS.builder()
                            .ip(cmtsDB.getIp())
                            .ipv6(cmtsDB.getIpv6())
                            .ipv4(cmtsDB.getIpv4())
                            .fqdn(cmtsDB.getName())
                            .mgtAreaName(cmtsDB.getMgtAreaName())
                            .regionName(cmtsDB.getRegionName())
                            .build();
                    break;
                }

            }
        }

        return cmts;
    }

    private RipRoute getRipData(List<CableModemDB> cableModemDataList, AccountDB ac) {

        if (cableModemDataList == null || cableModemDataList.size() == 0)
            return null;

        RipRoute ripRoute = null;

        for (CableModemDB cm :
                cableModemDataList) {
            if (cm.getAccountId().equals(ac.getRecordID())) {

                if (!isEmpty(cm.getNetwork())) {
                    ripRoute = RipRoute.builder()
                            .password("NA")
                            .network(cm.getNetwork())
                            .build();
                    break;
                }
            }
        }
        return ripRoute;
    }

    private String getBillingIDFromSoResponse(GetSpcAccountDivisionResponse soRes, AccountDB ac) {

        String billingID = null;

        if (soRes != null
                && soRes.getGetSpcAccountDivisionResponse() != null
                && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList() != null
                && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().size() > 0) {

            if (soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().size() == 1) {
                billingID = soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().get(0).getDivisionID();
            } else {
                // multiple account ICOMS scenario
                for (SpcAccountDivisionList t : soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList()) {
                    if (t.getZipCode5().equals(ac.getAddressZip())) {
                        billingID = t.getDivisionID();
                        break;
                    }
                }
            }
        }
        return billingID;
    }

    private Name getAccountHolderName(GetSpcAccountDivisionResponse soRes, AccountDB ac) {

        Name accountHolderName = null;

        if (soRes != null
                && soRes.getGetSpcAccountDivisionResponse() != null
                && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList() != null
                && soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList().size() > 0) {

            List<SpcAccountDivisionList> spcAccountDivisionList = soRes.getGetSpcAccountDivisionResponse().getSpcAccountDivisionList();

            if (spcAccountDivisionList.size() == 1) {
                accountHolderName = Name.builder()
                        .firstName(spcAccountDivisionList.get(0).getFirstName())
                        .lastName(spcAccountDivisionList.get(0).getLastName())
                        .businessName(spcAccountDivisionList.get(0).getBusinessName())
                        .build();


            } else {
                // multiple account ICOMS scenario
                for (SpcAccountDivisionList t : spcAccountDivisionList) {
                    if (t.getZipCode5().equals(ac.getAddressZip())) {
                        accountHolderName = Name.builder()
                                .firstName(t.getFirstName())
                                .lastName(t.getLastName())
                                .businessName(t.getBusinessName())
                                .build();
                        break;
                    }
                }
            }
        }
        return accountHolderName;
    }
}
