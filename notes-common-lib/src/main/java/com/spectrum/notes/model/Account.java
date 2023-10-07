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

/**
 * @author alam
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_sid", unique = true, nullable = false)
    private Long accountSid;*/

    @PrePersist
    private void prePersist() {
        accountSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "account_sid", unique = true, nullable = false)
    private UUID accountSid;

    private String account;

    @JsonProperty("address_apt")
    @Column(name = "address_apt")
    private String addressApt;

    @JsonProperty("address_city")
    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    @JsonProperty("address_state")
    private String addressState;

    @Column(name = "address_street")
    @JsonProperty("address_street")
    private String addressStreet;

    @Column(name = "address_zip")
    @JsonProperty("address_zip")
    private String addressZip;

    private String amp;

    @JsonProperty("area_id")
    @Column(name = "area_id")
    private String areaId;

    @Column(name = "cdf_id")
    @JsonProperty("cdf_id")
    private String cdfId;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;

    @JsonProperty("coordinates_longitude")
    @Column(name = "coordinates_longitude")
    private String coordinatesLongitude;

    @JsonProperty("coordinates_latitude")
    @Column(name = "coordinates_latitude")
    private String coordinatesLatitude;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "division_id")
    @JsonProperty("division_id")
    private String divisionId;

    @Column(name = "division_name")
    @JsonProperty("division_name")
    private String divisionName;

    @Column(name = "ecp_billing_division")
    private String ecpBillingDivision;

    private Float enabled;

    @JsonProperty("enterprise_id")
    @Column(name = "enterprise_id")
    private String enterpriseId;

    @JsonProperty("enterprise_name")
    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "geo_location")
    private String geoLocation;

    private String headend;

    @Column(name = "house_id")
    @JsonProperty("house_id")
    private String houseId;

    @Column(name = "house_name")
    @JsonProperty("house_name")
    private String houseName;

    @JsonProperty("hub_id")
    @Column(name = "hub_id")
    private String hubId;

    @JsonProperty("hub_name")
    @Column(name = "hub_name")
    private String hubName;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String lob_data;
    private String lob_mobile;
    private String lob_phone;
    private String lob_security;
    private String lob_uncategorized;
    private String lob_video;
    private String lob_wireless;

    @Column(name = "market_id")
    @JsonProperty("market_id")
    private String marketId;

    @JsonProperty("market_name")
    @Column(name = "market_name")
    private String marketName;

    @Column(name = "mgtArea_id")
    @JsonProperty("mgtArea_id")
    private String mgtAreaId;

    @JsonProperty("mgtArea_name")
    @Column(name = "mgtArea_name")
    private String mgtAreaName;

    private String name;

    @JsonProperty("node_id")
    @Column(name = "node_id")
    private String nodeId;

    @JsonProperty("node_name")
    @Column(name = "node_name")
    private String nodeName;

    @Column(columnDefinition = "TEXT", name = "old_id")
    @JsonProperty("old_id")
    private String oldId;

    private String phone;

    @JsonProperty("region_id")
    @Column(name = "region_id")
    private String regionId;

    @Column(name = "res_basic_video_sub")
    private String resBasicVideoSub;

    @Column(name = "res_HSD_sub")
    @JsonProperty("resHSDSub")
    private String resHSDSub;

    @Column(name = "res_voice_sub")
    private String resVoiceSub;

    private String scope;
    private Boolean seasonal;

    @Column(name = "start_date")
    private String startDate;

    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    @Column(name = "tech_ops_vip")
    private Boolean techOpsVip;

    private String type;
    private String vip;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}