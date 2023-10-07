package com.spectrum.notesapi.model.api.response.ripRoute;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @ApiModelProperty(notes = "address street", example = "219 GREENSLEEVES CT", allowEmptyValue = true)
    private String street;

    @ApiModelProperty(notes = "address city", example = "SANTA CLARITA", allowEmptyValue = true)
    private String city;

    @ApiModelProperty(notes = "address apt", example = "Q456", allowEmptyValue = true)
    private String apt;

    @ApiModelProperty(notes = "address zip", example = "'91350'", allowEmptyValue = true)
    private String zip;

    @ApiModelProperty(notes = "address state", example = "CA", allowEmptyValue = true)
    private String state;

    @ApiModelProperty(notes = "latitude", example = "'43.191360'", allowEmptyValue = true)
    private String latitude;

    @ApiModelProperty(notes = "longitudes", example = "'-85.93621'", allowEmptyValue = true)
    private String longitudes;
}