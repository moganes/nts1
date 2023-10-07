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

    @Column(name = "_key_")
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


    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}