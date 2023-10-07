package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "rip-password")
public class RipPasswordDB {

    private String clli;
    private String enterpriseName;
    private String insertDateTime;
    private String ripString;
}