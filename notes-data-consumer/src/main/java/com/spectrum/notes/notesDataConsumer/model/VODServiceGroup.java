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
@Table(name = "vod_service_group")
public class VODServiceGroup {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vod_service_group_sid", unique = true, nullable = false)
    private Long vodServiceGroupSid;*/

    @PrePersist
    private void prePersist() {
        vodServiceGroupSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "vod_service_group_sid", unique = true, nullable = false)
    private UUID vodServiceGroupSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    private Float enabled;

    @Column(name = "entity_type")
    private String entityType;

    @JsonProperty("hub_name")
    @Column(name = "hub_name")
    private String hubName;

    private String id;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    private String scope;

    private String type;

    @Column(name = "vod_service_group_name")
    private String vodServiceGroupName;
}