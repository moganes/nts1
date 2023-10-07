package com.spectrum.notesapi.model.api.response.deviceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 24 Jul, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEquipment {

    @ApiModelProperty(notes = "CM Mac", example = "688f2e5a8980", allowEmptyValue = true)
    private String mac;

    @ApiModelProperty(notes = "CM model", example = "TC8715D", allowEmptyValue = true)
    private String model;

    @ApiModelProperty(notes = "CM ip, ipv4 or ipv6, which ever applicable", example = "10.219.13.69", allowEmptyValue = true)
    private String ip;

    @ApiModelProperty(notes = "Equipment Type", example = "MTA, SetTopBox, CableModem", allowEmptyValue = true)
    private String equipmentType;

    @ApiModelProperty(notes = "Equipment make", example = "Technicolor", allowEmptyValue = true)
    private String make;

    @ApiModelProperty(notes = "When the record was received/created ", example = "2020-07-28T08:45:20.831", dataType = "String")
    private String insertDateTime;
}
