package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by "Mohammad Shah Alam" on 21 Oct, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Name {

    @ApiModelProperty(notes = "First Name of the residential customer", example = "Steven", allowEmptyValue = true)
    private String firstName;

    @ApiModelProperty(notes = "Last Name of the residential customer", example = "Lawman", allowEmptyValue = true)
    private String lastName;

    @ApiModelProperty(notes = "Business Name of the commercial customer", example = "ELYRIAS DRY CLEANERS", allowEmptyValue = true)
    private String businessName;
}
