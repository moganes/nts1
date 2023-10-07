package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RipRouteInfo {

    private Account account;
    private CMTS cmts;
    private RipRoute ripRoute;
}