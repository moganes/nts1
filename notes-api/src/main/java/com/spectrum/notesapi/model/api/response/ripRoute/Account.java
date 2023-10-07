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
public class Account {

    @ApiModelProperty(notes = "accountNumber", example = "'8260130120238202'", allowEmptyValue = true)
    private String accountNumber;

    @ApiModelProperty(notes = "billingID", example = "NA", allowEmptyValue = true)
    private String billingID;

    private Name name;

    @ApiModelProperty(notes = "Customer Type", example = "RESIDENTIAL | RESIDENTIAL_SINGLE_FAMILY | RESIDENTIAL_MULTI_FAMILY | COMMERCIAL | blank/null value", allowEmptyValue = true)
    private String customerType;

    private Address address;

}