package com.spectrum.notesapi.model.api.request.accountInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 30 Nov, 2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountInfoRequest {
    @ApiModelProperty(notes = "Account Number List", example = "'8260130120238202'", allowEmptyValue = true)
    private List<String> billingAccountNumber;
}
