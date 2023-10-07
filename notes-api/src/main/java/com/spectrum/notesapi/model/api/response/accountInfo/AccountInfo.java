package com.spectrum.notesapi.model.api.response.accountInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private String billingAccountNumber;
    private String accountHashId;
}