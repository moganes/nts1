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
@Table(name = "service_group")
public class ServiceGroup {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_group_sid", unique = true, nullable = false)
    private Long serviceGroupSid;*/

    @PrePersist
    private void prePersist() {
        serviceGroupSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "service_group_sid", unique = true, nullable = false)
    private UUID serviceGroupSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    private String direction;

    @Column(name = "entity_type")
    private String entityType;

    private String id;

    private String index;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    private String poller;

    private String scope;

    private String type;
}