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
public class CMTSRes {

    /*@ApiModelProperty(notes = "CMTS Device Vendor", example = "E.g Arris, Casa and many more", allowEmptyValue = true)
    private String vendor;*/

    @ApiModelProperty(notes = "CMTS hardwareVersion", example = "4.0", allowEmptyValue = true)
    private String hardwareVersion;

    @ApiModelProperty(notes = "CMTS ip", example = "10.219.13.69", allowEmptyValue = true)
    private String ip;

    @ApiModelProperty(notes = "CMTS ipv4", example = "10.219.13.69", allowEmptyValue = true)
    private String ipv4;

    @ApiModelProperty(notes = "CMTS ipv6", example = "2001:506:100:1000::3:4/128", allowEmptyValue = true)
    private String ipv6;

    @ApiModelProperty(notes = "CMTS sys description", example = "CER_V07.00.01.0021, <<HW_REV: 4.0; VENDOR: ARRIS; BOOTR: V00.01.00>>", allowEmptyValue = true)
    private String sysDescr;

    @ApiModelProperty(notes = "CMTS sys location", example = "444 Perry Road, Bangor, ME", allowEmptyValue = true)
    private String sysLoc;

    @ApiModelProperty(notes = "CMTS poller", example = "syrny-riobe-uni-02", allowEmptyValue = true)
    private String poller;

    @ApiModelProperty(notes = "CMTS sys location", example = "BANGOR : BA", allowEmptyValue = true)
    private String hubName;

    @ApiModelProperty(notes = "CMTS marketName", example = "fla", allowEmptyValue = true)
    private String marketName;

    @ApiModelProperty(notes = "CMTS managementAreaName", example = "scn", allowEmptyValue = true)
    private String managementAreaName;

    @ApiModelProperty(notes = "CMTS model", example = "E6000", allowEmptyValue = true)
    private String model;

    @ApiModelProperty(notes = "CMTS name or fqdn", example = "cts01sstlmo.chartercom.com", allowEmptyValue = true)
    private String name;

    @ApiModelProperty(notes = "CMTS region name ", example = "Northeast, Great Lakes, Central", allowEmptyValue = true)
    private String regionName;

    @ApiModelProperty(notes = "CMTS device serial ", example = "H06171128003687", allowEmptyValue = true)
    private String serial;

    @ApiModelProperty(notes = "CMTS softwareVersion ", example = "02.EF.03.10.00", allowEmptyValue = true)
    private String softwareVersion;

    @ApiModelProperty(notes = "CMTS make ", example = "02.EF.03.10.00", allowEmptyValue = true)
    private String make;

    @ApiModelProperty(notes = "CMTS macDomain", allowEmptyValue = true)
    private MacdomainRes macDomain;

    @ApiModelProperty(notes = "When the record was received/created ", example = "2020-07-28T08:45:20.831", dataType = "String")
    private String insertDateTime;
}
