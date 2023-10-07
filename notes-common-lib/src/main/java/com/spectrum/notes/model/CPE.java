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

/*** @author alam
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cpe")
public class CPE {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @private String Column(name = "cpe_sid";
     uprivate String nique = true;
      nullable = false)
    private Long cpe_sid;*/

    @PrePersist
    private void prePersist() {
        cpeSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "cpe_sid", unique = true, nullable = false)
    private UUID cpeSid;

    private String enterprise_name;
    private String model;
    private String scope;
    private String sys;

    @Column(name = "import_complete")
    private Boolean importComplete;

    private String hub_id;
    private String region_id;

    @Column(name = "cust_owned")
    private String custOwned;
    private String type;

    @Column(name = "entity_type")
    private String entityType;
    private String node_id;
    private String id;
    private String region_name;

    @Column(name = "sys_actual")
    private String sysActual;

    @Column(name = "install_date")
    private String installDate;

    private String name;
    private String hub_name;
    private String enterprise_id;
    private String key;
    private Float enabled;

    @Column(name = "equipment_type")
    private String equipmentType;
    private String account_id;

    @Column(name = "key_attr")
    private String keyAttr;


    @Column(name = "entity_name")
    private String entityName;
    private String account_name;
    private Boolean lob_data;
    private Boolean lob_phone;
    private Boolean lob_video;
    private String node_name;
    private String division_id;
    private String serial;
    private String make;
    private String mgtArea_id;
    private String mgtArea_name;

    @JsonProperty("area_id")
    @Column(name = "area_id")
    private String areaId;

    @JsonProperty("area_name")
    @Column(name = "area_name")
    private String areaName;

    @JsonProperty("cmts_id")
    @Column(name = "cmts_id")
    private String cmtsId;

    @JsonProperty("cmts_name")
    @Column(name = "cmts_name")
    private String cmtsName;

    @JsonProperty("division_name")
    @Column(name = "division_name")
    private String divisionName;

    @JsonProperty("hw_rev")
    @Column(name = "hw_rev")
    private String hwRev;

    @JsonProperty("hw_version")
    @Column(name = "hw_version")
    private String hwVersion;

    @JsonProperty("interface_id")
    @Column(name = "interface_id")
    private String interfaceId;

    private String lfb;

    @JsonProperty("market_id")
    @Column(name = "market_id")
    private String marketId;

    @JsonProperty("market_name")
    @Column(name = "market_name")
    private String marketName;

    @JsonProperty("selfInstall")
    @Column(name = "self_install")
    private String selfInstall;

    @JsonProperty("swVersion")
    @Column(name = "sw_version")
    private String swVersion;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}