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
@Entity
@Table(name = "awg")
public class AWG {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "awg_sid", unique = true, nullable = false)
    private Long awgSid;*/

    @PrePersist
    private void prePersist() {
        awgSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "awg_sid", unique = true, nullable = false)
    private UUID awgSid;

    private String account_id;
    private String account_name;
    private String addressable;
    private String area_id;
    private String area_name;
    private String bbip;
    private String callAgent_dns;
    private String callAgent_id;
    private String callAgent_imsId;

    @Column(name = "cat_description")
    @JsonProperty("catdescription")
    private String catdescription;

    @Column(name = "class")
    @JsonProperty("class")
    private String classA;

    private String cmts_id;
    private String cmts_name;
    private String cpe_id;

    @Column(name = "cust_owned")
    private String custOwned;

    private String digital;
    private String division_id;
    private String division_name;
    private String docsis_version;

    @Column(name = "downstream_id", columnDefinition = "TEXT")
    private String downstream_id;

    private String dvr;
    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "equipment_type")
    private String equipmentType;

    private String hdtv;
    private String hsd;
    private String hub_id;
    private String hub_name;
    private String hwrev;
    private String id;

    @Column(name = "import_complete")
    private Boolean importComplete;

    @Column(name = "install_date")
    private String installDate;

    @JsonProperty("interface_id")
    @Column(name = "interface_id")
    private String interfaceId;

    private String ip;
    private String ipv4;
    private String ipv6;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String lob;
    private String mac;
    private String make;
    private String market_id;
    private String mds;
    private String mgtArea_id;
    private String mgtArea_name;
    private String model;

    @Column(name = "model_description")
    private String modelDescription;

    private String mta_phoneNumber;
    private String name;
    private String node_id;
    private String occurrence;
    private String owner;
    private String phone;

    @Column(name = "pktc_domain")
    private String pktcDomain;

    private String port;
    private String privateId;

    @Column(name = "rc_name")
    private String rcName;

    private String region_id;
    private String region_name;
    private String scope;

    @JsonProperty("selfInstall")
    private String selfInstall;

    private String serial;

    @Column(name = "service_codes")
    @JsonProperty("service_codes")
    private String serviceCodes;

    @Column(name = "service_key")
    @JsonProperty("service_serviceKey")
    private String serviceKey;

    @Column(name = "service_type")
    @JsonProperty("service_serviceType")
    private String serviceType;


    @Column(name = "standard_code")
    @JsonProperty("standardcode")
    private String standardCode;

    @Column(name = "standard_type")
    @JsonProperty("standardtype")
    private String standardType;

    private String subtype;

    @Column(name = "sw_version")
    private String swVersion;

    @Column(name = "switch_type")
    private String switchType;

    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    @Column(name = "termination_prefix")
    private String terminationPrefix;

    private String type;
    private String vendor;


    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}