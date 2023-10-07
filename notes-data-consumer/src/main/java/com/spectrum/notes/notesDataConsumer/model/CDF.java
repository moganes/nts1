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
@Table(name = "cdf")
public class CDF {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cdf_sid", unique = true, nullable = false)
    private Long cdfSid;*/

    @PrePersist
    private void prePersist() {
        cdfSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "cdf_sid", unique = true, nullable = false)
    private UUID cdfSid;

    @Column(name = "cdf_string")
    @JsonProperty("CdfString")
    private String CdfString;

    @Column(name = "cdf_string_natural")
    @JsonProperty("CdfStringNatural")
    private String CdfStringNatural;
    private String agent;
    private String biller;
    private String division_id;
    private String division_name;
    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;
    private String glid;
    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "legacy_mso")
    private String legacyMSO;
    private String mgtArea_id;
    private String mgtArea_name;
    private String prin;
    private String region_id;
    private String region_name;
    private String scope;
    private String sys;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}