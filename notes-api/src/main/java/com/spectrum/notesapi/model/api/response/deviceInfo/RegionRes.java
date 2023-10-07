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
public class RegionRes {


    @ApiModelProperty(notes = "Region commercial video subscriber count", example = "2436763", allowEmptyValue = true)
    private String comBasicVideoSub;

    @ApiModelProperty(notes = "Region commercial HSD subscriber count", example = "866987", allowEmptyValue = true)
    private String comHSDSub;

    @ApiModelProperty(notes = "Region commercial voice subscriber count", example = "987", allowEmptyValue = true)
    private String comVoiceSub;

    @ApiModelProperty(notes = "Region residential video subscriber count", example = "6987", allowEmptyValue = true)
    private String resBasicVideoSub;

    @ApiModelProperty(notes = "Region residential HSD subscriber count", example = "28286", allowEmptyValue = true)
    private String resHSDSub;

    @ApiModelProperty(notes = "Region residential voice subscriber count", example = "987", allowEmptyValue = true)
    private String resVoiceSub;

    @ApiModelProperty(notes = "Region latitude", example = "28.2866987", allowEmptyValue = true)
    private String latitude;

    @ApiModelProperty(notes = "Region longitude", example = "longitude", allowEmptyValue = true)
    private String longitude;

    @ApiModelProperty(notes = "Region status ", example = "0/1", dataType = "String")
    private String enabled;

    @ApiModelProperty(notes = "Region name ", example = "Florida", dataType = "String")
    private String name;

    @ApiModelProperty(notes = "Region remedyName ", example = "Florida", dataType = "String")
    private String remedyName;

    @ApiModelProperty(notes = "When the record was received/created ", example = "2020-07-28T08:45:20.831", dataType = "String")
    private String insertDateTime;

}
