package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author alam
 */

@Data
@Builder
@Document(indexName = "properties-cmts")

@AllArgsConstructor
@NoArgsConstructor
public class CMTS {
    //@TODO: copied from wifi - check the usage and delete it
    private String docsis_vendor;
    private String hwVersion;
    private String ip;
    private String ipv4;
    private String ipv6;
    private String sysDescr;
    private String sysLoc;
    private String poller;
    private String hub_name;
    private String hub_id;
    private String make;
    private String market_name;
    private String mgtArea_name;
    private String model;
    private String name;
    private String region_name;
    private String region_id;
    private String serial;
    private String swVersion;
    private String insertDateTime;
}
