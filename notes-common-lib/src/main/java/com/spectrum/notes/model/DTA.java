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
@Table(name = "dta")
public class DTA {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dta_sid", unique = true, nullable = false)
    private Long dta_sid;*/

    @PrePersist
    private void prePersist() {
        dta_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "dta_sid", unique = true, nullable = false)
    private UUID dta_sid;

    private String account_id;
    private String account_name;
    private String addressable;
    private String area_id;
    private String area_name;

    private String bbip;

    @Column(name = "cat_description")
    @JsonProperty("catdescription")
    private String catDescription;

    @Column(name = "cmts_id")
    @JsonProperty("cmts_id")
    private String cmtsId;

    @Column(name = "cmts_name")
    @JsonProperty("cmts_name")
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

    private String hub_id;
    private String hardware_rev;
    private String hardware_version;
    private String hdtv;
    private String hsd;
    private String hub_name;
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
    private String market_name;
    private String mds;
    private String mgtArea_id;
    private String mgtArea_name;
    private String model;

    @Column(name = "model_description")
    private String modelDescription;

    private String node_id;
    private String node_name;
    private String name;

    private String occurrence;
    private String owner;
    private String phone;

    @Column(name = "pktc_domain")
    private String pktcDomain;

    private String poller;
    private String port;

    @Column(name = "port_type")
    @JsonProperty("porttype")
    private String portType;

    @Column(name = "self_install")
    private String selfInstall;

    private String region_id;
    private String region_name;
    private String scope;
    private String serial;

    @Column(name = "service_codes")
    @JsonProperty("service_codes")
    private String serviceCodes;

    @Column(name = "standard_code")
    @JsonProperty("standardcode")
    private String standardCode;

    @Column(name = "standard_type")
    @JsonProperty("standardtype")
    private String standardType;

    private String subtype;
    private String swVersion;
    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}