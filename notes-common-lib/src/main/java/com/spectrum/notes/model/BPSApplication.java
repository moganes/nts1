package com.spectrum.notes.model;

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
@Table(name = "bps_application")
public class BPSApplication {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bps_application_sid", unique = true, nullable = false)
    private Long bpsApplicationSid;*/

    @PrePersist
    private void prePersist() {
        bpsApplicationSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "bps_application_sid", unique = true, nullable = false)
    private UUID bpsApplicationSid;

    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;
    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;
    private String module_id;
    private String name;
    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}