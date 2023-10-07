package com.spectrum.notesapi.model.api.request.deviceInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceInfoSearchCriteria {

    @ApiModelProperty(notes = "Account Number", example = "'8260130120238202'", allowEmptyValue = true)
    private String billingAccountNumber;

    @ApiModelProperty(notes = "Cable Modem mac", example = "688f2e5a8980", allowEmptyValue = true)
    private String cmMac;


}
