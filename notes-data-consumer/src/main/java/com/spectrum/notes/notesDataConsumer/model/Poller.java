package com.spectrum.notes.notesDataConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poller")
public class Poller {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poller_sid", unique = true, nullable = false)
    private Long poller_sid;*/

    @PrePersist
    private void prePersist() {
        poller_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "poller_sid", unique = true, nullable = false)
    private UUID poller_sid;


    private Float enabled;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}
