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
@Table(name = "house")
public class House {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_sid", unique = true, nullable = false)
    private Long house_sid;*/

    @PrePersist
    private void prePersist() {
        house_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "house_sid", unique = true, nullable = false)
    private UUID house_sid;

    private String active;
    private String cdf_id;
    private String city;
    private String division_id;
    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "house_num")
    private String housenum;

    private String hub_id;
    private String hub_name;
    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String latitude;
    private String longitude;
    private String mgtArea_id;
    private String mgtArea_name;
    private String name;
    private String node_id;
    private String node_name;
    private String region_id;
    private String region_name;
    private String scope;
    private String state;
    private String street;
    private String sys;
    private String type;
    private String zip;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}