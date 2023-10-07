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
@Table(name = "area")
public class Area {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_sid", unique = true, nullable = false)
    private Long areaSid;*/

    @PrePersist
    private void prePersist() {
        areaSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "area_sid", unique = true, nullable = false)
    private UUID areaSid;

    private String ancestors;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;
    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String market_id;
    private String name;
    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}