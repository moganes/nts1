package com.spectrum.notes.notesDataConsumer.model.complexEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    private String id;

    @JsonProperty("cmtsid")
    private String cmtSid;
}