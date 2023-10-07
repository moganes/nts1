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
@Table(name = "bps_site")
public class BPSSite {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bps_site_sid", unique = true, nullable = false)
    private Long bpsSiteSid;*/

    @PrePersist
    private void prePersist() {
        bpsSiteSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "bps_site_sid", unique = true, nullable = false)
    private UUID bpsSiteSid;

    private String bpsapplication_id;
    private Integer enabled;
    private String bpssite_name;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;
    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;
    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}