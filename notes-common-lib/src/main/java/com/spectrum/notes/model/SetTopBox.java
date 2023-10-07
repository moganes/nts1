package com.spectrum.notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author alam
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "set_top_box")
public class SetTopBox {
 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_top_box_sid", unique = true, nullable = false)
    private Long setTopBoxSid;*/

    @PrePersist
    private void prePersist() {
        setTopBoxSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "set_top_box_sid", unique = true, nullable = false)
    private UUID setTopBoxSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    @Column(name = "account_id")
    @JsonProperty("account_id")
    private String accountId;

    @Column(name = "account_name")
    @JsonProperty("account_name")
    private String accountName;

    private String addressable;

    @Column(name = "area_id")
    @JsonProperty("area_id")
    private String areaId;

    @Column(name = "area_name")
    @JsonProperty("area_name")
    private String areaName;

    @Column(name = "cpe_id")
    @JsonProperty("cpe_id")
    private String cpeId;

    @Column(name = "cust_owned")
    private String custOwned;

    @Column(name = "division_id")
    @JsonProperty("division_id")
    private String divisionId;

    @Column(name = "division_name")
    @JsonProperty("division_name")
    private String divisionName;


    private String docsis_version;
    private Double enabled;

    @Column(name = "enterprise_id")
    @JsonProperty("enterprise_id")
    private String enterpriseId;

    @Column(name = "enterprise_name")
    @JsonProperty("enterprise_name")
    private String enterpriseName;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "equipment_type")
    private String equipmentType;

    @Column(name = "hub_id")
    @JsonProperty("hub_id")
    private String hubId;

    @Column(name = "hub_name")
    @JsonProperty("hub_name")
    private String hubName;

    private String id;

    @Column(name = "import_complete")
    private Boolean importComplete;

    @Column(name = "install_date")
    private String installDate;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String make;

    @Column(name = "market_id")
    @JsonProperty("market_id")
    private String marketId;

    @Column(name = "market_name")
    @JsonProperty("market_name")
    private String marketName;

    @Column(name = "mgt_area_id")
    @JsonProperty("mgtArea_id")
    private String mgtAreaId;

    @Column(name = "mgt_area_name")
    @JsonProperty("mgtArea_name")
    private String mgtAreaName;

    private String model;

    private String name;

    @Column(name = "node_id")
    @JsonProperty("node_id")
    private String nodeId;

    @Column(name = "node_name")
    @JsonProperty("node_name")
    private String nodeName;

    private String port;

    @Column(name = "region_id")
    @JsonProperty("region_id")
    private String regionId;

    @Column(name = "region_name")
    @JsonProperty("region_name")
    private String regionName;

    private String scope;

    private String serial;

    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;
    private String bbip;

    @Column(name = "cable_modem_id")
    @JsonProperty("cableModem_id")
    private String cableModemId;

    @Column(name = "call_agent_dns")
    @JsonProperty("callAgent_dns")
    private String callAgentDns;

    @Column(name = "call_agent_id")
    @JsonProperty("callAgent_id")
    private String callAgentId;

    @Column(name = "downstream_id")
    @JsonProperty("downstream_id")
    private String downstreamId;

    @Column(name = "hw_rev")
    @JsonProperty("hw_rev")
    private String hwRev;

    @Column(name = "hw_version")
    @JsonProperty("hw_version")
    private String hwVersion;

    @Column(name = "interface_id", columnDefinition = "TEXT")
    @JsonProperty("interface_id")
    private String interfaceId;

    private String ip;
    private String ipv4;
    private String ipv6;
    private String lfb;
    private String lob;
    private String mac;

    @Column(name = "mta_phone_number")
    private String mtaPhoneNumber;

    @Column(name = "pktc_domain")
    @JsonProperty("pktc_domain")
    private String pktcDomain;

    private String privateId;

    @Column(name = "rc_name")
    private String rcName;

    @Column(name = "self_install")
    private String selfInstall;

    @Column(name = "service_codes")
    @JsonProperty("service_codes")
    private String serviceCodes;

    @Column(name = "service_key")
    @JsonProperty("service_serviceKey")
    private String serviceKey;

    @Column(name = "service_type")
    @JsonProperty("service_serviceType")
    private String serviceType;

    private String subtype;
    private String swVersion;

    @Column(name = "switch_type")
    @JsonProperty("switch_switchType")
    private String switchType;

    @Column(name = "termination_prefix")
    private String terminationPrefix;

    public UUID getSetTopBoxSid() {
        return setTopBoxSid;
    }

    public void setSetTopBoxSid(UUID setTopBoxSid) {
        this.setTopBoxSid = setTopBoxSid;
    }

    public ZonedDateTime getInsertDateTime() {
        return insertDateTime;
    }

    public void setInsertDateTime(ZonedDateTime insertDateTime) {
        this.insertDateTime = insertDateTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAddressable() {
        return addressable;
    }

    public void setAddressable(String addressable) {
        this.addressable = addressable;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCpeId() {
        return cpeId;
    }

    public void setCpeId(String cpeId) {
        this.cpeId = cpeId;
    }

    public String getCustOwned() {
        return custOwned;
    }

    public void setCustOwned(String custOwned) {
        this.custOwned = custOwned;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDocsis_version() {
        return docsis_version;
    }

    public void setDocsis_version(String docsis_version) {
        this.docsis_version = docsis_version;
    }

    public Double getEnabled() {
        return enabled;
    }

    public void setEnabled(Double enabled) {
        this.enabled = enabled;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        entityName = StringEscapeUtils.escapeJava(entityName);
        this.entityName = entityName;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getImportComplete() {
        return importComplete;
    }

    public void setImportComplete(Boolean importComplete) {
        this.importComplete = importComplete;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        key = StringEscapeUtils.escapeJava(key);
        this.key = key;
    }

    public String getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(String keyAttr) {
        this.keyAttr = keyAttr;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMgtAreaId() {
        return mgtAreaId;
    }

    public void setMgtAreaId(String mgtAreaId) {
        this.mgtAreaId = mgtAreaId;
    }

    public String getMgtAreaName() {
        return mgtAreaName;
    }

    public void setMgtAreaName(String mgtAreaName) {
        this.mgtAreaName = mgtAreaName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = StringEscapeUtils.escapeJava(name);
        this.name = name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        serial = StringEscapeUtils.escapeJava(serial);
        this.serial = serial;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getSysActual() {
        return sysActual;
    }

    public void setSysActual(String sysActual) {
        this.sysActual = sysActual;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBbip() {
        return bbip;
    }

    public void setBbip(String bbip) {
        this.bbip = bbip;
    }

    public String getCableModemId() {
        return cableModemId;
    }

    public void setCableModemId(String cableModemId) {
        this.cableModemId = cableModemId;
    }

    public String getCallAgentDns() {
        return callAgentDns;
    }

    public void setCallAgentDns(String callAgentDns) {
        this.callAgentDns = callAgentDns;
    }

    public String getCallAgentId() {
        return callAgentId;
    }

    public void setCallAgentId(String callAgentId) {
        this.callAgentId = callAgentId;
    }

    public String getDownstreamId() {
        return downstreamId;
    }

    public void setDownstreamId(String downstreamId) {
        this.downstreamId = downstreamId;
    }

    public String getHwRev() {
        return hwRev;
    }

    public void setHwRev(String hwRev) {
        this.hwRev = hwRev;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getLfb() {
        return lfb;
    }

    public void setLfb(String lfb) {
        this.lfb = lfb;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMtaPhoneNumber() {
        return mtaPhoneNumber;
    }

    public void setMtaPhoneNumber(String mtaPhoneNumber) {
        this.mtaPhoneNumber = mtaPhoneNumber;
    }

    public String getPktcDomain() {
        return pktcDomain;
    }

    public void setPktcDomain(String pktcDomain) {
        this.pktcDomain = pktcDomain;
    }

    public String getPrivateId() {
        return privateId;
    }

    public void setPrivateId(String privateId) {
        this.privateId = privateId;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public String getSelfInstall() {
        return selfInstall;
    }

    public void setSelfInstall(String selfInstall) {
        this.selfInstall = selfInstall;
    }

    public String getServiceCodes() {
        return serviceCodes;
    }

    public void setServiceCodes(String serviceCodes) {
        this.serviceCodes = serviceCodes;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getSwVersion() {
        return swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    public String getSwitchType() {
        return switchType;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public String getTerminationPrefix() {
        return terminationPrefix;
    }

    public void setTerminationPrefix(String terminationPrefix) {
        this.terminationPrefix = terminationPrefix;
    }
}