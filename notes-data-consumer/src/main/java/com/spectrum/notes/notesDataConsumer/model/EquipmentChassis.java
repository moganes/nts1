package com.spectrum.notes.notesDataConsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "equipment_chassis")
public class EquipmentChassis {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_chassis_sid", unique = true, nullable = false)
    private Long equipment_chassis_sid;*/

    @PrePersist
    private void prePersist() {
        equipment_chassis_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "equipment_chassis_sid", unique = true, nullable = false)
    private UUID equipment_chassis_sid;


    @Column(name = "class")
    @JsonProperty("class")
    private String clazz;

    private String cmts_id;
    private String descr;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "fw_version")
    private String fwVersion;

    @Column(name = "hw_version")
    private String hwVersion;

    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String make;
    private String model;
    private String name;
    private String poller;
    private String scope;
    private String serial;

    @Column(name = "sw_version")
    private String swVersion;

    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

}