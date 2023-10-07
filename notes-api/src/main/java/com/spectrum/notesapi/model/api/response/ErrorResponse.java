package com.spectrum.notesapi.model.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @ApiModelProperty(notes = "List of errors",
            example = "[\"Internal server, bad request error\"]",
            dataType = "array")

    private List<String> errors;

    @ApiModelProperty(notes = "Date time", example = "Tue Oct 14 17:00:43 MST 2020", dataType = "String")
    private String timestamp;

    @ApiModelProperty(notes = "http status code and message", example = "400 | 500 INTERNAL_SERVER_ERROR", dataType = "String")
    private String status;
}