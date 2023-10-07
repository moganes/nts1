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
@Table(name = "qpsk_demod")
public class QPSKDemod {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qpsk_demod_sid", unique = true, nullable = false)
    private Long qpskDemodSid;*/

    @PrePersist
    private void prePersist() {
        qpskDemodSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "qpsk_demod_sid", unique = true, nullable = false)
    private UUID qpskDemodSid;

    private String division_id;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;
    private String qpsk_id;
    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}