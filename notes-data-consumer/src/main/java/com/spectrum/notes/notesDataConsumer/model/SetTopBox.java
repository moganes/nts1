package com.spectrum.notes.notesDataConsumer.model;

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
@Table(name = "set_top_box")
public class SetTopBox {
 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_top_box_sid", unique = true, nullable = false)
    private Long setTopBoxSid;*/

    @PrePersist
    private void prePersist() {
        setTopBoxSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "set_top_box_sid", unique = true, nullable = false)
    private UUID setTopBoxSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    @Column(name = "account_id")
    @JsonProperty("account_id")
    private String accountId;

    @Column(name = "account_name")
    @JsonProperty("account_name")
    private String accountName;

    private String addressable;

    @Column(name = "area_id")
    @JsonProperty("area_id")
    private String areaId;

    @Column(name = "area_name")
    @JsonProperty("area_name")
    private String areaName;

    @Column(name = "cpe_id")
    @JsonProperty("cpe_id")
    private String cpeId;

    @Column(name = "cust_owned")
    private String custOwned;

    @Column(name = "division_id")
    @JsonProperty("division_id")
    private String divisionId;

    @Column(name = "division_name")
    @JsonProperty("division_name")
    private String divisionName;

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

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String make;

    @Column(name = "market_id")
    @JsonProperty("market_id")
    private String marketId;

    @Column(name = "market_name")
    @JsonProperty("market_name")
    private String marketName;

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
}