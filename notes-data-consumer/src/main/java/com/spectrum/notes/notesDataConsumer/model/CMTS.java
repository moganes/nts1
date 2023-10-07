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
@Table(name = "cmts")
public class CMTS {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmts_sid", unique = true, nullable = false)
    private Long cmtsSid;*/

    @PrePersist
    private void prePersist() {
        cmtsSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "cmts_sid", unique = true, nullable = false)
    private UUID cmtsSid;

    private String enterprise_name;
    private String market_name;
    private String model;
    private String scope;
    private String pollerId;
    private String docsis_vendor;
    private String hub_id;
    private String region_id;
    private String docsis_natural;

    @Column(name = "modified_by")
    private String modifiedby;

    private String type;

    @Column(name = "entity_type")
    private String entityType;
    private String impact;

    @Column(name = "trap_class")
    private String trapClass;

    private String id;
    private String region_name;
    private String bb_page;
    private String host_id;
    private String name;
    private String poller;
    private String enterprise_id;
    private String prevPollerId;
    @Column(name = "_key_")
    private String key;
    private String bb_group;
    private Float enabled;
    private String sortOrder;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "tftp_server")
    private String tftpserver;

    private String mgtArea_name;

    @Column(name = "created_by")
    private String createdby;

    private String division_name;
    private String ip;
    private String docsis_affiliate;
    private String market_id;
    private String bb_ip;

    @Column(name = "created_time")
    private String createdtime;

    private String bb_service;

    @Column(name = "modified_time")
    private String modifiedtime;

    private String docsis_model;
    private String division_id;
    private String make;
    private String mgtArea_id;

    @Column(name = "sw_version")
    private String swVersion;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}