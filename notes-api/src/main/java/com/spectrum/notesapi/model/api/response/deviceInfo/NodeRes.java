package com.spectrum.notesapi.model.api.response.deviceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 20 May, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NodeRes {
    @ApiModelProperty(notes = "Node name", example = "16DB187", allowEmptyValue = true)
    private String name;
}
