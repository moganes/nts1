package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RipRoute {

    @ApiModelProperty(notes = "network", example = "96.38.87.252/30", allowEmptyValue = true)
    private String network;

    @ApiModelProperty(notes = "password", example = "NA", allowEmptyValue = true)
    private String password;
}