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
public class MacdomainRes {
    @ApiModelProperty(notes = "macDomain name", example = "cable2/0/6",allowEmptyValue = true)
    private String name;

    private NodeRes node;
}
