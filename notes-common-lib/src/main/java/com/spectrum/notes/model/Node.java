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
@Table(name = "node")
public class Node {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_sid", unique = true, nullable = false)
    private Long node_sid;*/

    @PrePersist
    private void prePersist() {
        node_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "node_sid", unique = true, nullable = false)
    private UUID node_sid;

    private String address_city;
    private String address_state;
    private String address_street;
    private String address_zip;
    private String area_id;
    private String cmts_id;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_video_sub")
    private String comVideoSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;

    private String coordinates_latitude;
    private String coordinates_longitude;
    private String division_id;
    private String division_name;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "geo_location")
    private String geoLocation;

    private String hub_id;
    private String hub_name;
    private String id;

    @Column(name = "interface_list_upstream_hashkeys", columnDefinition = "TEXT")
    private String interfaceListUpstream_hashKeys;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String legacy_id;
    private String market_id;
    private String mgtArea_id;
    private String mgtArea_name;
    private String name;
    private String node_name;

    @Column(columnDefinition = "TEXT")
    private String old_id;

    @Column(name = "primary_tech")
    private String primaryTech;

    private String rawName_name;
    private String region_id;
    private String region_name;
    private String remedy_hub;
    private String remedy_mgtArea;
    private String remedy_name;
    private String remedy_region;

    @Column(name = "res_basic_video_sub")
    private String resBasicVideoSub;

    @Column(name = "res_HSD_sub")
    @JsonProperty("resHSDSub")
    private String resHSDSub;

    @Column(name = "res_video_sub")
    private String resVideoSub;

    @Column(name = "res_voice_sub")
    private String resVoiceSub;

    @Column(name = "sbv_service_group")
    private String sbvServiceGroup;

    private String scope;
    private String sdvsg_id;
    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String team_id;
    private String type;
    private Boolean verified;
    private String virtual;

    @Column(name = "vod_service_group")
    private String vodServiceGroup;

    private String vodsg_id;

    @Column(name = "active_sub")
    private String activeSub;

    @Column(name = "basic_sub")
    private String basicSub;

    @Column(name = "bpssite_id")
    @JsonProperty("bpssite_id")
    private String bpssiteId;

    private String clli;
    private String demod;

    @Column(name = "demod_id")
    @JsonProperty("demod_id")
    private String demodId;

    @Column(name = "former_sub")
    private String formerSub;

    @Column(name = "has_com")
    private String hasCom;

    @Column(name = "has_res")
    private String hasRes;

    @Column(name = "homes_passed")
    private String homesPassed;

    @Column(name = "interface_hashKeys")
    @JsonProperty("interface_hashKeys")
    private String interfaceHashKeys;

    @Column(name = "never_sub")
    private String neverSub;

    @Column(name = "node_name1")
    private String nodeName;

    @Column(name = "npt_id")
    @JsonProperty("npt_id")
    private String nptId;

    @Column(name = "qpsk_id")
    @JsonProperty("qpsk_id")
    private String qpskId;

    @Column(name = "qpsk_name")
    @JsonProperty("qpsk_name")
    private String qpskName;

    @Column(name = "remedy_tech_group")
    private String remedyTechGroup;

    @Column(name = "remedy_division")
    @JsonProperty("remedy_division")
    private String remedyDivison;

    @Column(name = "remedy_market")
    @JsonProperty("remedy_market")
    private String remedyMarket;

    private String split;
    private String supervisor;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}