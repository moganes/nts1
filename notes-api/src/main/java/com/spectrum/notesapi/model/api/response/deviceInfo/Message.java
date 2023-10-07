package com.spectrum.notesapi.model.api.response.deviceInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 30 Jul, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    @ApiModelProperty(notes = "API response message", example = "No data found for the request e.g No record found for mac=7cb21b94b142x")
    private String message;
}
