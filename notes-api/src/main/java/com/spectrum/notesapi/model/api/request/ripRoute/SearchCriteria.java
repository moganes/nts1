package com.spectrum.notesapi.model.api.request.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCriteria {

    @ApiModelProperty(notes = "Account Number", example = "'8260130120238202'", allowEmptyValue = true)
    private String billingAccountNumber;

    @ApiModelProperty(notes = "First Name in case of Residential customer", example = "Steven", allowEmptyValue = true)
    private String accountFirstName;

    @ApiModelProperty(notes = "Last Name in case of Residential customer", example = "Lawman", allowEmptyValue = true)
    private String accountLastName;

    @ApiModelProperty(notes = "Business name in case of Commercial customer", example = "ELYRIAS DRY CLEANERS", allowEmptyValue = true)
    private String businessName;

    @ApiModelProperty(notes = "zip5 of account holder address", example = "'91367'", allowEmptyValue = true)
    private String zipCode5;

}
