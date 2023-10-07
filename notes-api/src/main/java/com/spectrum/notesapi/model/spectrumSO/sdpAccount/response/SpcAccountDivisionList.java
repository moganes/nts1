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
public class SpcAccountDivisionList {
    private String locationTimeZone;
    private String lastName;
    private String city;
    private String zipCode4;
    private String zipCode5;
    private String businessName;
    private String billingStationLevel2Code;
    private String accountStatus;
    private String emailAddress;
    private String billingStationLevel0Code;
    private String soloAccountNumber;
    private String soloLocationNumber;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String billingStationLevel1Code;
    private String dbroot;
    private AccountType accountType;
    private String sourceMSO;
    private String soloDBInstanceID;
    private String accountNumber;
    private String sourceFTACode;
    private String uCAN;
    private String firstName;
    private String primaryPhone;
    private String otherPhone;
    private String billingSystem;
    private String divisionID;
    private String secondaryPhone;
}

