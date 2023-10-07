package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by "Mohammad Shah Alam" on 19 Oct, 2020
 */

@Data
@Builder
@Document(indexName = "properties-cmts")
@AllArgsConstructor
@NoArgsConstructor
public class CmtsDB {

    private String ip;
    private String ipv4;
    private String ipv6;
    private String name;
    private String insertDateTime;
    private String recordID;


    @Field("docsis_vendor")
    private String docsisVendor;


    private String hwVersion;
    private String sysDescr;
    private String sysLoc;
    private String poller;

    @Field("docsis_vendor")
    private String hub_name;

    @Field("hub_id")
    private String hubId;
    private String make;

    @Field("market_name")
    private String marketName;

    @Field("mgtArea_name")
    private String mgtAreaName;
    private String model;

    @Field("region_name")
    private String regionName;

    @Field("region_id")
    private String regionId;
    private String serial;
    private String swVersion;
}
