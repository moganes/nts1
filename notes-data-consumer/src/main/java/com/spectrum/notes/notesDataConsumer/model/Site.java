package com.spectrum.notes.notesDataConsumer.model;

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
@Table(name = "site")
public class Site {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_sid", unique = true, nullable = false)
    private Long siteSid;*/

    @PrePersist
    private void prePersist() {
        siteSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "site_sid", unique = true, nullable = false)
    private UUID siteSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    private Double enabled;

    @Column(name = "entity_type")
    private String entityType;

    private String id;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    private String type;
}