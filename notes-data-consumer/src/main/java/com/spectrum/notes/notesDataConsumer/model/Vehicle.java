/*
package com.spectrum.notes.notesDataConsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

*/
/**
 * @author alam
 *//*

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_sid", unique = true, nullable = false)
    private Long vehicleSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    @JsonProperty("cityMPG")
    @Column(name = "city_mpg")
    private Double cityMpg;

    @Column(name = "current_odometer")
    private Double currentOdometer;

    @Column(name = "delta_distance")
    private Double deltaDistance;

    @Column(name = "delta_time")
    private Double deltaTime;

    private Double direction;

    @Column(name = "display_state")
    private String displayState;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_number")
    private String driverNumber;

    private Double enabled;

    @Column(name = "engine_minutes")
    private Double engineMinutes;


    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "geo_location")
    private String geoLocation;

    @Column(name = "group_id")
    private String groupID;

    @Column(name = "has_navigation_device")
    private Boolean hasNavigationDevice;

    private String heading;

    @Column(name = "highway_mpg")
    private Double highwayMPG;

    private String id;

    @Column(name = "idle_time")
    private Double idleTime;

    @Column(name = "private")
    private Boolean isPrivate;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private Double latitude;

    @Column(name = "location_update_utc")
    private Double locationUpdateUTC;

    private Double longitude;

    private String make;

    private String model;

    private String name;

    private String number;

    @Column(name = "registration_number")
    private String registrationNumber;

    private String scope;

    private Double size;

    private Double speed;

    @Column(name = "tank_capacity")
    private Double tankCapacity;

    @Column(name = "status_update_utc")
    private Double statusUpdateUTC;

    private String type;

    private String vin;

    private Double year;
}*/
