package com.spectrum.notesapi.model.api.response.ripRoute;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spectrum.notesapi.model.api.request.ripRoute.SMBStaticIpRipInforRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by "Mohammad Shah Alam" on 1 April, 2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMBStaticIpRipInfoResponse {

    private SMBStaticIpRipInforRequest request;
    private List<ResponseInfo> response;

}
