package com.spectrum.notesapi.model.api.request.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 1 April, 2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMBStaticIpRipInforRequest {

    @ApiModelProperty(notes = "Static IPv4 of SMB customer", example = "'76.79.152.252'", allowEmptyValue = false)
    private String staticIP;
}
