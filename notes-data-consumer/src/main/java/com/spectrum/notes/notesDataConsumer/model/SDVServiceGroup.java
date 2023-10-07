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
@Table(name = "sdv_service_group")
public class SDVServiceGroup {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sdv_service_group_sid", unique = true, nullable = false)
    private Long sdvServiceGroupSid;*/

    @PrePersist
    private void prePersist() {
        sdvServiceGroupSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "sdv_service_group_sid", unique = true, nullable = false)
    private UUID sdvServiceGroupSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    private Double enabled;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "hub_name")
    @JsonProperty("hub_name")
    private String hubName;

    private String id;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    private String scope;

    @Column(name = "sdv_service_group_name")
    private String sdvServiceGroupName;

    private String type;
}