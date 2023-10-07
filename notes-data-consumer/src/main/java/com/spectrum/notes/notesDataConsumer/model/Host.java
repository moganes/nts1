package com.spectrum.notes.notesDataConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/*** @author alam
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "host")
public class Host {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_sid", unique = true, nullable = false)
    private Long host_sid;*/

    @PrePersist
    private void prePersist() {
        host_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "host_sid", unique = true, nullable = false)
    private UUID host_sid;

    @Column(name = "bb_group")
    private String bbgroup;

    @Column(name = "bb_ip")
    private String bbip;

    @Column(name = "bb_page")
    private String bbpage;

    @Column(name = "bb_service")
    private String bbservice;

    @Column(name = "created_by")
    private String createdby;

    @Column(name = "created_time")
    private String createdtime;

    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    private String host_id;

    private String id;
    private String impact;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String market_id;

    @Column(name = "modified_by")
    private String modifiedby;

    @Column(name = "modified_time")
    private String modifiedtime;

    private String poller_id;
    private String poller_prevId;
    private String scope;
    private String sort_order;

    @Column(name = "trap_class")
    private String trapClass;
    private String type;
    private String name;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}