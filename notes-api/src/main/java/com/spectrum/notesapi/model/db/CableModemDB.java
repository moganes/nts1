package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "properties-cablemodem")
public class CableModemDB {

    @Field("cmts_name")
    private String cmtsName;
    private String insertDateTime;

    @Field("cmts_id")
    private String cmts_id;

    @Field("account_id")
    private String accountId;

    private String network;
    private String recordID;


    private String mac;
    private String model;
    private String ip;
    private String equipmentType;
    private String account_name;
    private String hub_name;
    private String make;

    @Field("node_name")
    private String nodeName;

    @Field("macDomain_id")
    private String macDomainId;
}