package com.spectrum.notesapi.model.spectrumSO.sdpAccount.request;

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
public class QueryFields {

    private String lastName;
    private String firstName;
   // private final String systemID = "Gateway";
    private final String systemID = "NOTES";
    private String zipCode5;
    private String businessName;
    private String accountNumber;
}
