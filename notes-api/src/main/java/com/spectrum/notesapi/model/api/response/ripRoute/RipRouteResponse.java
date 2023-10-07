package com.spectrum.notesapi.model.api.response.ripRoute;

import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
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
public class RipRouteResponse {

    private SearchCriteria request;
    private List<RipRouteInfo> response;
}