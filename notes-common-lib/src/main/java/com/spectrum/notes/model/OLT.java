package com.spectrum.notes.model;

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
@Entity
@Table(name = "olt")
public class OLT {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "olt_sid", unique = true, nullable = false)
    private Long olt_sid;*/

    @PrePersist
    private void prePersist() {
        olt_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "olt_sid", unique = true, nullable = false)
    private UUID olt_sid;

    @Column(name = "created_by")
    private String createdby;

    private String poller;

    @Column(name = "sw_version")
    private String swVersion;

    @Column(name = "entity_type")
    private String entityType;

    private String ip;

    @Column(name = "sys_loc")
    private String sysLoc;

    private String market_id;
    private String type;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "trap_class")
    private String trapClass;

    @Column(name = "created_time")
    private String createdtime;

    private Float enabled;
    private String market_name;

    @Column(name = "hw_version")
    private String hwVersion;

    @Column(name = "sys_descr")
    private String sysDescr;

    @Column(name = "modified_time")
    private String modifiedtime;


    private String scope;
    private String name;

    @Column(name = "modified_by")
    private String modifiedby;

    private String model;
    private String id;
    private String make;

    @Column(name = "tftp_server")
    private String tftpserver;

    private String key;
    private String ipv4;
    private String ipv6;
    private String division_id;
    private String division_name;
    private String enterprise_id;
    private String enterprise_name;
    private String hub_id;
    private String hub_name;
    private String mgtArea_id;
    private String mgtArea_name;
    private String region_id;
    private String region_name;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}