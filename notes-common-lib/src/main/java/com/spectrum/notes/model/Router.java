package com.spectrum.notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author alam
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "router")
public class Router {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_sid", unique = true, nullable = false)
    private Long routerSid;*/

    @PrePersist
    private void prePersist() {
        routerSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "router_sid", unique = true, nullable = false)
    private UUID routerSid;

    @Column(name = "account_id")
    @JsonProperty("account_id")
    private String accountId;

    @Column(name = "account_name")
    @JsonProperty("account_name")
    private String accountName;

    private String addressable;

    @Column(name = "cpe_id")
    @JsonProperty("cpe_id")
    private String cpeId;

    @Column(name = "cust_owned")
    private String custOwned;

    @Column(name = "division_id")
    @JsonProperty("division_id")
    private String divisionId;

    private Double enabled;

    @Column(name = "enterprise_id")
    @JsonProperty("enterprise_id")
    private String enterpriseId;

    @Column(name = "enterprise_name")
    @JsonProperty("enterprise_name")
    private String enterpriseName;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "equipment_type")
    private String equipmentType;

    @Column(name = "hub_id")
    @JsonProperty("hub_id")
    private String hubId;

    @Column(name = "hub_name")
    @JsonProperty("hub_name")
    private String hubName;

    private String id;

    @Column(name = "import_complete")
    private Boolean importComplete;

    @Column(name = "install_date")
    private String installDate;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String make;

    @Column(name = "mgt_area_id")
    @JsonProperty("mgtArea_id")
    private String mgtAreaId;

    @Column(name = "mgt_area_name")
    @JsonProperty("mgtArea_name")
    private String mgtAreaName;

    private String model;

    private String name;

    @Column(name = "node_id")
    @JsonProperty("node_id")
    private String nodeId;

    @Column(name = "node_name")
    @JsonProperty("node_name")
    private String nodeName;

    private String port;

    @Column(name = "region_id")
    @JsonProperty("region_id")
    private String regionId;

    @Column(name = "region_name")
    @JsonProperty("region_name")
    private String regionName;

    private String scope;

    private String serial;

    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;

    @Column(name = "area_id")
    @JsonProperty("area_id")
    private String areaId;

    @Column(name = "area_name")
    @JsonProperty("area_name")
    private String areaName;

    private String bbip;

    @Column(name = "cat_description")
    @JsonProperty("catdescription")
    private String catDescription;

    @Column(name = "cmts_id")
    @JsonProperty("cmts_id")
    private String cmtsId;

    @Column(name = "cmts_name")
    @JsonProperty("cmts_name")
    private String cmts_name;

    @Column(name = "device_type")
    @JsonProperty("device_type")
    private String deviceType;

    private String digital;

    @Column(name = "division_name")
    @JsonProperty("division_name")
    private String divisionName;

    @Column(name = "docsis_version")
    @JsonProperty("docsis_version")
    private String docsisVersion;

    @Column(name = "downstream_id")
    @JsonProperty("downstream_id")
    private String downstreamId;
    private String dvr;
    private String hdtv;
    private String hsd;

    @Column(name = "hw_rev")
    @JsonProperty("hw_rev")
    private String hwRev;

    @Column(name = "hw_version")
    @JsonProperty("hw_version")
    private String hwVersion;

    @Column(name = "interface_id")
    @JsonProperty("interface_id")
    private String interfaceId;
    private String ip;
    private String ipv4;
    private String ipv6;
    private String lfb;
    private String lob;
    private String mac;

    @Column(name = "market_id")
    @JsonProperty("market_id")
    private String marketId;

    @Column(name = "market_name")
    @JsonProperty("market_name")
    private String marketName;
    private String mds;

    @Column(name = "model_description")
    private String modelDescription;

    private String occurrence;
    private String owner;
    private String phone;

    @Column(name = "pktc_domain")
    @JsonProperty("pktc_domain")
    private String pktcDomain;

    private String poller;

    @Column(name = "port_type")
    @JsonProperty("porttype")
    private String portType;

    @Column(name = "self_install")
    private String selfInstall;

    @Column(name = "service_codes")
    @JsonProperty("service_codes")
    private String serviceCodes;

    @Column(name = "standard_code")
    @JsonProperty("standardcode")
    private String standardCode;

    @Column(name = "standard_type")
    @JsonProperty("standardtype")
    private String standardType;

    private String subtype;
    private String swVersion;


    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}