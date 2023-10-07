package com.spectrum.notes.dao;

import com.spectrum.notes.model.CMTS;
import com.spectrum.notes.model.CableModem;
import com.spectrum.notes.model.Hub;
import com.spectrum.notes.models.CableModemDaily;
import com.spectrum.notes.models.CableModemData;
import com.spectrum.notes.models.CmtsDaily;
import com.spectrum.notes.repository.CMTSRepository;
import com.spectrum.notes.repository.CableModemRepository;
import com.spectrum.notes.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CmtsDailyService {

    private final CMTSRepository cmtsRepository;
    private final HubRepository hubRepository;
    private final CableModemRepository cableModemRepository;

    @Autowired
    public CmtsDailyService(CMTSRepository cmtsRepository,
                            HubRepository hubRepository,
                            CableModemRepository cableModemRepository) {
        this.cmtsRepository = cmtsRepository;
        this.hubRepository = hubRepository;
        this.cableModemRepository = cableModemRepository;
    }

    public CompletableFuture<Optional<CmtsDaily>> getCmtsDailyById(String cmtsId) {
        return CompletableFuture.supplyAsync(() -> {
            CMTS cmts = cmtsRepository.findTopByDocsisNaturalOrderByInsertDateTimeDesc(cmtsId);
            Hub hub = null;
            CmtsDaily.CmtsDailyBuilder result = null;
            if (cmts != null) {
                hub = hubRepository.findTopByHubSidOrderByInsertDateTimeDesc(cmts.getHubId());
                result = CmtsDaily.builder()
                        .addressCity(null)
                        .addressState(null)
                        .addressStreet(null)
                        .addressZip(null)
                        .clli(cmts.getDocsisNatural())
                        .equipmentId(null)
                        .hardwareBootVersion(null)
                        .hardwareVersion(null)
                        .id(cmts.getId())
                        .ipAddress(cmts.getIp())//cmts.getBbIp()
                        .latitude(null)
                        .lineCardSerialNumber(null)
                        .locationId(null)
                        .locationName(null)
                        .longitude(null)
                        .macAddress(null)
                        .marketingArea(cmts.getMarketName())
                        .model(cmts.getModel())
                        .name(cmts.getName())
                        .operatingSystem(null)
                        .operatingSystemVersion(null)
                        .region(cmts.getRegion_name())
                        .serialNumber(null)
                        .systemDescription(null)
                        .vendor(cmts.getDocsisVendor());
            }
            if (hub != null) {
                if(result == null)
                    result = CmtsDaily.builder();
                result
                        .hubAddressCity(hub.getAddress_city())
                        .hubAddressState(hub.getAddress_state())
                        .hubAddressStreet(hub.getAddress_street())
                        .hubAddressZip(hub.getAddress_zip())
                        .hubLatitude(hub.getCoordinates_latitude())
                        .hubLongitude(hub.getCoordinates_longitude())
                        .hubName(hub.getHubName());
            }

           if(result != null)
               return Optional.of(result.build());
           else
               return Optional.empty();
        });
    }

//    public CompletableFuture<Optional<Collection<CableModem5Min>>> getCableModem5MinByCmts(String cmtsId) {
//        return CompletableFuture.supplyAsync(() -> {
//            CableModem5Min cableModem5Min;
//            cableModem5Min.
//            Query query = new Query(String.format("select last(mac_address), * from CM_5 where cmts_clli = '%s' and cmts_clli_hash = '%d' group by * order by time desc", cmtsId, getCmtsHash(cmtsId, 10)), "network_elements");
//            QueryResult result = influxDB.query(query);
//            List<CableModem5Min> cableModem5Mins = new InfluxDBResultMapper().toPOJO(result, CableModem5Min.class);
//            if (cableModem5Mins.size() == 0) {
//                return Optional.empty();
//            } else {
//                return Optional.of(cableModem5Mins);
//            }
//        });
//    }

    public CompletableFuture<Optional<Collection<CableModemDaily>>> getCableModemDailyByCmts(String cmtsId) {
        return CompletableFuture.supplyAsync(() -> {
            List<CableModem> cableModems = cableModemRepository.findTopNByCmtsId(cmtsId + ".%");
            if (cableModems.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(cableModems.parallelStream()
                        .map(cableModem -> {
                            return CableModemDaily.builder()
                                    .cmtsClli(cableModem.getCmts_name())
                                    .cmtsIpAddress(null)
                                    .cmtsMacAddress(null)
                                    .docsisVersion(cableModem.getDocsis_version())
                                    .firmware(null)
                                    .ipAddress(cableModem.getIp())
                                    .macAddress(cableModem.getMac())
                                    .model(cableModem.getModel())
                                    .vendor(cableModem.getMake())
                                    .build();
                        })
                        .collect(Collectors.toSet()));
            }
        });
    }

    public CompletableFuture<Optional<Collection<CableModemData>>> getCableModemByCmts(String cmtsId) {
        return CompletableFuture.supplyAsync(() -> {

            Map<String, CableModemData> results = new HashMap<>();
            try {
                CompletableFuture<Optional<Collection<CableModemDaily>>> dailyFuture = getCableModemDailyByCmts(cmtsId);
//                CompletableFuture<Optional<Collection<CableModem5Min>>> fiveMinFuture = getCableModem5MinByCmts(cmtsId);
                Optional<Collection<CableModemDaily>> cmDaily = dailyFuture.get();
//                Optional<Collection<CableModem5Min>> cm5Min = fiveMinFuture.get();
//                cm5Min.ifPresent(cableModem5Mins -> cableModem5Mins.forEach(cableModem5Min -> results.put(cableModem5Min.getMacAddress(), CableModem.builder().cableModem5Min(cableModem5Min).cableModemDaily(null).build())));
                cmDaily.ifPresent(cableModemDailies -> cableModemDailies.forEach(cableModemDaily -> {
                    CableModemData cm = results.get(cableModemDaily.getMacAddress());
                    if (cm == null) {
                        results.put(cableModemDaily.getMacAddress(), CableModemData.builder().cableModem5Min(null).cableModemDaily(cableModemDaily).build());
                    } else {
                        cm.setCableModemDaily(cableModemDaily);
                    }
                }));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return results.isEmpty() ? Optional.empty() : Optional.of(results.values());
        });
    }

    int getCmtsHash(String cmtsId, int modulo) {
        return cmtsId.codePoints().sum() % modulo;
    }
}
