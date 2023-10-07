package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmbAccount {

    @ApiModelProperty(notes = "accountNumber", example = "'8260130120238202'", allowEmptyValue = true)
    private String accountNumber;

    @ApiModelProperty(notes = "soaDivisionID", example = "CVG.003", allowEmptyValue = true)
    private String soaDivisionID;

    @ApiModelProperty(notes = "billingID", example = "KYA.003", allowEmptyValue = true)
    private String spcDivisionID;
}