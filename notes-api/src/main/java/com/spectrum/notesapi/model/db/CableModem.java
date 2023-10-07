package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "properties-cablemodem")
public class CableModem {

    // TODO: refactor and remove
    private String mac;
    private String model;
    private String ip;
    private String equipmentType;
    private String insertDateTime;
    private String cmts_id;
    private String account_id;
    private String account_name;
    private String hub_name;
}