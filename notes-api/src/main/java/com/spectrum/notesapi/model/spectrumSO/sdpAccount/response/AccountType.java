package com.spectrum.notesapi.model.spectrumSO.sdpAccount.response;

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
public class AccountType {

    private String longDescription;
    private String enterpriseCode;
    private String description;
    private String billerCode;

}
