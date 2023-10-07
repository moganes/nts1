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
////@JsonIgnoreProperties(ignoreUnknown = true)
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
    private String amp;
    private String apt;
    private String cdf_id;
    private String city;

    @Column(name = "customer_type")
    private String customerType;

    private String division_id;

    @Column(name = "division_name")
    @JsonProperty("division_name")
    private String divisionName;

    @Column(name = "ecp_billing_division")
    private String ecpBillingDivision;

    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "fk_cdf")
    @JsonProperty("fkCDF")
    private String fkcdf;

    @Column(name = "geo_location")
    private String geoLocation;

    private String headend;

    @Column(name = "house_num")
    private String housenum;

    private String hub_id;
    private String hub_name;
    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String lastSeen;

    private String latitude;
    private String longitude;
    private String mgtArea_id;
    private String mgtArea_name;
    private String name;
    private String node_id;
    private String node_name;
    private String old_id;
    private String power;
    private String region_id;
    private String region_name;
    private String scope;
    private String state;
    private String street;
    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;
    private String zip;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}