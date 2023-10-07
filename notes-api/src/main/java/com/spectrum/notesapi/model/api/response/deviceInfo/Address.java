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
public class Address {

    @ApiModelProperty(notes = "address zip", example = "91350", allowEmptyValue = true)
    private String zip;

    @ApiModelProperty(notes = "address city", example = "SANTA CLARITA", allowEmptyValue = true)
    private String city;

    @ApiModelProperty(notes = "address apt", example = "1456", allowEmptyValue = true)
    private String apt;

    @ApiModelProperty(notes = "address state", example = "CA", allowEmptyValue = true)
    private String state;

    @ApiModelProperty(notes = "address street", example = "21719 GREENSLEEVES CT", allowEmptyValue = true)
    private String street;

    @ApiModelProperty(notes = "Latitude,Longitude", example = "43.191360,-85.93621", allowEmptyValue = true)
    private String geoLocation;

    @ApiModelProperty(notes = "When the record was received/created ", example = "2020-07-28T08:45:20.831", dataType = "String")
    private String insertDateTime;
}
