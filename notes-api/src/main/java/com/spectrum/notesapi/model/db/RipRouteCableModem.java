package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by "Mohammad Shah Alam" on 05 Apr, 2021
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "notes-riproutedata")
//@Document(indexName = "notes-reports-riproutedata")
public class RipRouteCableModem {
    private String network;
    private String cmts_name;
    private String ip;
    private String mac;
    private String account_name;
    private String mgtArea_name;
    private String enterprise_name;
    private String insertDateTime;
    private String lastseen;
    private String account_id;
    private String recordID;
    private String soaDivisionID;
    private String spcDivisionID;
}
