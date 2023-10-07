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
@Table(name = "mta")
public class Mta {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mta_sid", unique = true, nullable = false)
    private Long mta_sid;*/

    @PrePersist
    private void prePersist() {
        mta_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "mta_sid", unique = true, nullable = false)
    private UUID mta_sid;

    private String account_id;
    private String account_name;
    private String addressable;
    private String area_id;
    private String area_name;
    private String bbip;

    @Column(name = "cableModem_id")
    @JsonProperty("cableModem_id")
    private String cableModemId;

    @Column(name = "callAgent_dns")
    @JsonProperty("callAgent_dns")
    private String callAgentDns;

    @Column(name = "callAgent_id")
    @JsonProperty("callAgent_id")
    private String callAgentId;

    @Column(name = "callAgent_imsId")
    @JsonProperty("callAgent_imsId")
    private String callAgentImsId;

    @Column(name = "class")
    @JsonProperty("class")
    private String classA;


    private String cmts_id;
    private String cmts_name;

    private String cpe_id;

    @Column(name = "cust_owned")
    private String custOwned;

    private String division_id;
    private String division_name;

    @Column(name = "docsis_version")
    @JsonProperty("docsis_version")
    private String docsisVersion;

    @Column(name = "downstream_id", columnDefinition = "TEXT")
    @JsonProperty("downstream_id")
    private String downstreamId;

    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "equipment_type")
    private String equipmentType;

    private String hub_id;
    private String hub_name;
    private String hw_rev;
    private String hw_version;
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
    private String mgtArea_id;
    private String mgtArea_name;
    private String model;

    @Column(name = "mta_phone_number")
    private String mtaPhoneNumber;

    private String name;
    private String node_id;
    private String node_name;
    private String pktc_domain;
    private String port;
    private String privateId;
    private String proxy;
    private String ratecenter_id;
    private String ratecenter_name;

    @Column(name = "rc_name")
    private String rcName;

    private String region_id;
    private String region_name;
    private String scope;

    @JsonProperty("selfInstall")
    @Column(name = "self_install")
    private String selfInstall;

    private String serial;
    private String service_codes;

    @Column(name = "service_key")
    @JsonProperty("service_serviceKey")
    private String serviceKey;

    @Column(name = "service_type")
    @JsonProperty("service_serviceType")
    private String serviceType;


    private String serving;
    private String sipuri;
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