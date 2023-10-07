package com.spectrum.notesapi.model.api.response.deviceInfo;

import com.spectrum.notesapi.model.api.request.deviceInfo.DeviceInfoSearchCriteria;
import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import com.spectrum.notesapi.model.api.response.ripRoute.RipRouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoResponse {

    private DeviceInfoSearchCriteria request;
    private List<DeviceInfoFullByMACResponse> response;
}