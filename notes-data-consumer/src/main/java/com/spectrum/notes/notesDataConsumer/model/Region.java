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
@Table(name = "region")
public class Region {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_sid", unique = true, nullable = false)
    private Long regionSid;*/

    @PrePersist
    private void prePersist() {
        regionSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "region_sid", unique = true, nullable = false)
    private UUID regionSid;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;

    @Column(name = "coordinates_latitude")
    @JsonProperty("coordinates_latitude")
    private String coordinatesLatitude;

    @Column(name = "coordinates_longitude")
    @JsonProperty("coordinates_longitude")
    private String coordinatesLongitude;

    private Double enabled;

    @Column(name = "enterprise_id")
    @JsonProperty("enterprise_id")
    private String enterpriseId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "geo_location")
    private String geoLocation;

    private String id;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    @Column(name = "remedy_name")
    @JsonProperty("remedy_name")
    private String remedyName;

    @Column(name = "res_basic_video_sub")
    private String resBasicVideoSub;

    @Column(name = "res_HSD_sub")
    @JsonProperty("resHSDSub")
    private String resHSDSub;

    @Column(name = "res_voice_sub")
    private String resVoiceSub;

    private String scope;

    @Column(name = "site_id")
    @JsonProperty("site_id")
    private String siteId;

    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}