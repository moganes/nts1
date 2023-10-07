package com.spectrum.notesapi.model.api.response.deviceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 04 Aug, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoFullByMACResponse {

    @ApiModelProperty(notes = "Account Information")
    private AccountRes accountInfo;

    @ApiModelProperty(notes = "Equipment on account List")
    private List<AccountEquipment> accountEquipmentList;

    @ApiModelProperty(notes = "CMTS")
    private CMTSRes cmts;

    @ApiModelProperty(notes = "RegionRes")
    private RegionRes region;

}
