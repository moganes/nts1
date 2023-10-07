package com.spectrum.notesapi.model.spectrumSO.sdpAccount.response;

import com.spectrum.notesapi.model.api.request.ripRoute.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 03 Nov, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSpcAccountDivisionResponse {

    private ResponseHolder getSpcAccountDivisionResponse;
    private SearchCriteria sc;
}
