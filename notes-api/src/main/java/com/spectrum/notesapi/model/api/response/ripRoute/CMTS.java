package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CMTS {
    @ApiModelProperty(notes = "CMTS ip", example = "10.219.13.69", allowEmptyValue = true)
    private String ip;

    @ApiModelProperty(notes = "CMTS ipv4", example = "10.219.13.69", allowEmptyValue = true)
    private String ipv4;

    @ApiModelProperty(notes = "CMTS ipv6", example = "2001:506:100:1000::3:4/128", allowEmptyValue = true)
    private String ipv6;

    @ApiModelProperty(notes = "CMTS name or fqdn", example = "cts01sstlmo.chartercom.com", allowEmptyValue = true)
    private String fqdn;

    @ApiModelProperty(notes = "CMTS mgtAreaName", example = "scr", allowEmptyValue = true)
    private String mgtAreaName;

    @ApiModelProperty(notes = "CMTS regionName", example = "car", allowEmptyValue = true)
    private String regionName;
}