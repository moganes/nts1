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
@Table(name = "division")
public class Division {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_sid", unique = true, nullable = false)
    private Long divisionSid;*/

    @PrePersist
    private void prePersist() {
        divisionSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "division_sid", unique = true, nullable = false)
    private UUID divisionSid;

    private String ancestors;
    private String area_id;

    @Column(name = "b_dncs_server")
    @JsonProperty("bDNCSServer")
    private String bDNCSServer;

    @Column(name = "billing_db")
    private String billingDB;

    @Column(name = "bms_file_name")
    private String bmsFileName;

    private String bpssite_id;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;

    private String coordinates_latitude;
    private String coordinates_longitude;

    @Column(name = "current_UTC_offset")
    private String currentUTCOffset;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "geo_location")
    private String geoLocation;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String market_id;

    @Column(name = "mas_server")
    private String masServer;
    private String name;
    private String nyroc_divisionName;
    private String nyroc_name;
    private String nyroc_server;
    private String olsonTZ;

    @Column(name = "p_DNSC_server")
    @JsonProperty("pDNCSServer")
    private String pDNCSServer;

    @Column(name = "pk_system")
    private String pkSystem;
    private String remedy_market;
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
    private String type;
    private String unified_cpeDb;
    private String unified_divisionID;
    private String unified_divisionName;
    private String unified_friendlyName;
    private String unified_server;
    private String unified_webURL;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}