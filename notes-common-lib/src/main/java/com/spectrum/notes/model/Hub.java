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
@Table(name = "hub")
public class Hub {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_sid", unique = true, nullable = false)
    private Long hub_sid;*/

    @PrePersist
    private void prePersist() {
        hub_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "hub_sid", unique = true, nullable = false)
    private UUID hub_sid;

    private String address_city;
    private String address_state;
    private String address_street;
    private String address_zip;

    @Column(name = "alt_hub_iD")
    private String altHubID;

    private String ancestors;
    private String area_id;

    @Column(name = "bpssite_id")
    @JsonProperty("bpssite_id")
    private String bpssiteId;

    private String clli;

    @JsonProperty("cmts_id")
    @Column(name = "cmts_id")
    private String cmtsId;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;


    private String coordinates_latitude;
    private String coordinates_longitude;
    private String division_id;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    private String facility_id;

    @Column(name = "geo_location")
    private String geoLocation;

    @Column(name = "has_com")
    private String hasCom;

    @Column(name = "has_res")
    private String hasRes;

    @Column(name = "hub_common_name")
    private String hubCommonName;

    @Column(name = "hub_name")
    private String hubName;

    @Column(name = "hub_type")
    private String hubType;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String market_id;
    private String mgtArea_id;
    private String mgtArea_name;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String old_id;

    private String region_id;

    @Column(name = "remedy_division")
    @JsonProperty("remedy_division")
    private String remedyDivison;

    @Column(name = "remedy_market")
    @JsonProperty("remedy_market")
    private String remedyMarket;

    private String remedy_mgtArea;
    private String remedy_name;
    private String remedy_region;

    @Column(name = "res_basic_video_sub")
    private String resBasicVideoSub;

    @Column(name = "res_HSD_sub")
    @JsonProperty("resHSDSub")
    private String resHSDSub;

    @Column(name = "res_voice_sub")
    private String resVoiceSub;
    private String scope;

    @Column(name = "site_status")
    private String siteStatus;

    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;

    private Boolean verified;
    private String virtual;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}