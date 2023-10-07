package com.spectrum.notes.model;

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
@Table(name = "equipment_back_plane")
public class EquipmentBackPlane {
   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_back_plane_sid", unique = true, nullable = false)
    private Long equipment_back_plane_sid;
*/
    @PrePersist
    private void prePersist() {
        equipment_back_plane_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "equipment_back_plane_sid", unique = true, nullable = false)
    private UUID equipment_back_plane_sid;


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