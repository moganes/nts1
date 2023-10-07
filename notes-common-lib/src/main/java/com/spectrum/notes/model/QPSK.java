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
@Table(name = "qpsk")
public class QPSK {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qpsk_sid", unique = true, nullable = false)
    private Long qpskSid;*/

    @PrePersist
    private void prePersist() {
        qpskSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "qpsk_sid", unique = true, nullable = false)
    private UUID qpskSid;

    private String bbip;
    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String hub_id;
    private String id;
    private String ip;
    private String ipv4;
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