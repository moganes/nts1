package com.spectrum.notes.models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//@Measurement(name = "CMTS_Daily", timeUnit = TimeUnit.NANOSECONDS)
public class CmtsDaily {

//    @Column(name = "address_street")
    private String addressStreet;
//    @Column(name = "address_city")
    private String addressCity;
//    @Column(name = "address_state", tag = true)
    private String addressState;
//    @Column(name = "address_zip")
    private String addressZip;
//    @Column(name = "latitude", tag = true)
    private String latitude;
//    @Column(name = "longitude", tag = true)
    private String longitude;
//    @Column(name = "equipment_id", tag = true)
    private String equipmentId;
//    @Column(name = "hub_address_street")
    private String hubAddressStreet;
//    @Column(name = "hub_address_city")
    private String hubAddressCity;
//    @Column(name = "hub_address_state", tag = true)
    private String hubAddressState;
//    @Column(name = "hub_address_zip")
    private String hubAddressZip;
//    @Column(name = "hub_latitude", tag = true)
    private String hubLatitude;
//    @Column(name = "hub_longitude", tag = true)
    private String hubLongitude;
//    @Column(name = "hub_name", tag = true)
    private String hubName;
//    @Column(name = "id", tag = true)
    private String id;
//    @Column(name = "ip_address", tag = true)
    private String ipAddress;
//    @Column(name = "location_name", tag = true)
    private String locationName;
//    @Column(name = "marketing_area", tag = true)
    private String marketingArea;
//    @Column(name = "name", tag = true)
    private String name;
//    @Column(name = "region", tag = true)
    private String region;
//    @Column(name = "model", tag = true)
    private String model;
//    @Column(name = "system_description")
    private String systemDescription;
//    @Column(name = "vendor", tag = true)
    private String vendor;
//    @Column(name = "location_id", tag = true)
    private String locationId;
//    @Column(name = "serial_number", tag = true)
    private String serialNumber;
//    @Column(name = "hardware_boot_version", tag = true)
    private String hardwareBootVersion;
//    @Column(name = "line_card_serial_number", tag = true)
    private String lineCardSerialNumber;
//    @Column(name = "line_card_vendor_model", tag = true)
    private String lineCardVendorModel;
//    @Column(name = "operating_system", tag = true)
    private String operatingSystem;
//    @Column(name = "operating_system_version", tag = true)
    private String operatingSystemVersion;
//    @Column(name = "hardware_version", tag = true)
    private String hardwareVersion;
//    @Column(name = "clli", tag = true)
    private String clli;
//    @Column(name = "mac_address", tag = true)
    private String macAddress;
}
