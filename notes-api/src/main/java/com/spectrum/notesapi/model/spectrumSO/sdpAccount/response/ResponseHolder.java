package com.spectrum.notesapi.model.spectrumSO.sdpAccount.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 03 Nov, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseHolder {

    private String searchLimitReached;
    private List<SpcAccountDivisionList> spcAccountDivisionList;
    private PartialErrorList[] partialErrorList;
}
