package com.spectrum.notesapi.service;

import com.spectrum.notesapi.model.api.request.ripRoute.SMBStaticIpRipInforRequest;
import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 15 Oct, 2020
 */
public interface RipRouteService {
    ResponseEntity<?> getRipRouteInfo(List<SearchCriteria> searchCriteriaList, String tracingID, String clientApp);

    ResponseEntity<?> getRipRouteInfoByStaticIp(List<SMBStaticIpRipInforRequest> staticIpList, String tracingID, String clientApp);
}
