package com.spectrum.notesapi.model.api.response.deviceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 04 Aug, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRes {

    @ApiModelProperty(notes = "8150200071196239")
    private String accountNumber;

    @ApiModelProperty(notes = "CAR.202")
    private String soaDivisionID;

    @ApiModelProperty(notes = "CAR.202")
    private String spcDivisionID;

    @ApiModelProperty(notes = "Account address")
    private Address address;
}
