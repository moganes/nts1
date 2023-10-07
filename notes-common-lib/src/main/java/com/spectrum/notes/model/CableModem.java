package com.spectrum.notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/*** @author alam
 */

//@JsonIgnoreProperties(ignoreUnknown = true)
//@Data

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "cable_modem")
public class CableModem {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cable_modem_sid", unique = true, nullable = false)
    private Long cable_modem_sid;*/

    @PrePersist
    private void prePersist() {
        cableModemSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "cable_modem_sid", unique = true)
    private UUID cableModemSid;

    private String enterprise_name;
    private String market_name;
    private String region_id;
    private String type;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "downstream_id", columnDefinition = "TEXT")
    private String downstream_id;

    @Column(name = "bootversion", columnDefinition = "TEXT")
    private String bootVersion;
    private String region_name;

    @Column(name = "sys_actual")
    private String sysActual;

    private String cmts_id;
    private String cpe_id;
    private String enterprise_id;
    private String key;
    private Float enabled;

    @Column(name = "equipment_type")
    private String equipmentType;
    private String account_id;
    private String docsis_version;
    private String cmts_name;

    @Column(name = "key_attr")
    private String keyAttr;

    private Boolean fbc_capable;
    private String mgtArea_name;

    @Column(name = "tftp_server")
    private String tftpServer;

    private String account_name;
    private String node_name;
    private String make;

    @Column(name = "sw_version")
    private String swVersion;

    private String addressable;
    private String port;
    private Boolean fbc_enabled;
    private String area_id;
    private String ipv4;
    private String model;
    private String scope;
    private String sys;

    @Column(name = "import_complete")
    private Boolean importComplete;

    private String hub_id;

    @Column(name = "cust_owned")
    private Boolean custOwned;
    private String mac;
    private String node_id;
    private String id;

    @Column(name = "hw_version")
    private String hwVersion;

    @Column(name = "install_date")
    private String installDate;

    private String area_name;
    private String name;
    private String hub_name;

    @Column(columnDefinition = "TEXT")
    private String upstream_id;

    private String division_name;
    private String ip;
    private String macDomain_id;
    private String market_id;
    private String division_id;
    private String serial;
    private String mgtArea_id;

    @Column(name = "battery_last_change")
    @JsonProperty("battery_batteryLastChange")
    private String batteryLastChange;

    @Column(name = "battery_message")
    @JsonProperty("battery_batteryMessage")
    private String batteryMessage;

    @Column(name = "battery_minutes")
    @JsonProperty("battery_batteryMinutes")
    private String batteryMinutes;

    @Column(name = "battery_status")
    @JsonProperty("battery_batteryStatus")
    private String batteryStatus;

    @Column(name = "docsis_isp")
    @JsonProperty("docsis_isp")
    private String docsisIsp;

    private String ipv6;

    @Column(name = "last_poll")
    @JsonProperty("lastPoll")
    private String lastPoll;

    private String lastseen;
    private String lob;
    private String poller;

    @Column(name = "self_install")
    @JsonProperty("selfInstall")
    private Boolean selfInstall;

    @Column(name = "service_codes")
    @JsonProperty("service_codes")
    private String serviceCodes;

    @Column(name = "service_type")
    @JsonProperty("service_type")
    private String serviceType;

    private Integer status;

    @Column(name = "sys_descr", columnDefinition = "TEXT")
    private String sysDescr;

    @Column(name = "sys_loc")
    private String sysLoc;

    private String uptime;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    public UUID getCableModemSid() {
        return cableModemSid;
    }

    public void setCableModemSid(UUID cableModemSid) {
        this.cableModemSid = cableModemSid;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getDownstream_id() {
        return downstream_id;
    }

    public void setDownstream_id(String downstream_id) {
        this.downstream_id = downstream_id;
    }

    public String getBootVersion() {
        return bootVersion;
    }

    public void setBootVersion(String bootVersion) {
        // bootVersion = bootVersion.replaceAll("\\u0000", "");
        bootVersion = StringEscapeUtils.escapeJava(bootVersion);
        //  bootVersion = bootVersion.trim();
        this.bootVersion = bootVersion;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getSysActual() {
        return sysActual;
    }

    public void setSysActual(String sysActual) {
        this.sysActual = sysActual;
    }

    public String getCmts_id() {
        return cmts_id;
    }

    public void setCmts_id(String cmts_id) {
        this.cmts_id = cmts_id;
    }

    public String getCpe_id() {
        return cpe_id;
    }

    public void setCpe_id(String cpe_id) {
        this.cpe_id = cpe_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Float getEnabled() {
        return enabled;
    }

    public void setEnabled(Float enabled) {
        this.enabled = enabled;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getDocsis_version() {
        return docsis_version;
    }

    public void setDocsis_version(String docsis_version) {
        this.docsis_version = docsis_version;
    }

    public String getCmts_name() {
        return cmts_name;
    }

    public void setCmts_name(String cmts_name) {
        this.cmts_name = cmts_name;
    }

    public String getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(String keyAttr) {
        this.keyAttr = keyAttr;
    }

    public Boolean getFbc_capable() {
        return fbc_capable;
    }

    public void setFbc_capable(Boolean fbc_capable) {
        this.fbc_capable = fbc_capable;
    }

    public String getMgtArea_name() {
        return mgtArea_name;
    }

    public void setMgtArea_name(String mgtArea_name) {
        this.mgtArea_name = mgtArea_name;
    }

    public String getTftpServer() {
        return tftpServer;
    }

    public void setTftpServer(String tftpServer) {
        this.tftpServer = tftpServer;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getSwVersion() {
        return swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    public String getAddressable() {
        return addressable;
    }

    public void setAddressable(String addressable) {
        this.addressable = addressable;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getFbc_enabled() {
        return fbc_enabled;
    }

    public void setFbc_enabled(Boolean fbc_enabled) {
        this.fbc_enabled = fbc_enabled;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public Boolean getImportComplete() {
        return importComplete;
    }

    public void setImportComplete(Boolean importComplete) {
        this.importComplete = importComplete;
    }

    public String getHub_id() {
        return hub_id;
    }

    public void setHub_id(String hub_id) {
        this.hub_id = hub_id;
    }

    public Boolean getCustOwned() {
        return custOwned;
    }

    public void setCustOwned(Boolean custOwned) {
        this.custOwned = custOwned;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHub_name() {
        return hub_name;
    }

    public void setHub_name(String hub_name) {
        this.hub_name = hub_name;
    }

    public String getUpstream_id() {
        return upstream_id;
    }

    public void setUpstream_id(String upstream_id) {
        this.upstream_id = upstream_id;
    }

    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacDomain_id() {
        return macDomain_id;
    }

    public void setMacDomain_id(String macDomain_id) {
        macDomain_id = StringEscapeUtils.escapeJava(macDomain_id);
        this.macDomain_id = macDomain_id;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getDivision_id() {
        return division_id;
    }

    public void setDivision_id(String division_id) {
        this.division_id = division_id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMgtArea_id() {
        return mgtArea_id;
    }

    public void setMgtArea_id(String mgtArea_id) {
        this.mgtArea_id = mgtArea_id;
    }

    public String getBatteryLastChange() {
        return batteryLastChange;
    }

    public void setBatteryLastChange(String batteryLastChange) {
        this.batteryLastChange = batteryLastChange;
    }

    public String getBatteryMessage() {
        return batteryMessage;
    }

    public void setBatteryMessage(String batteryMessage) {
        this.batteryMessage = batteryMessage;
    }

    public String getBatteryMinutes() {
        return batteryMinutes;
    }

    public void setBatteryMinutes(String batteryMinutes) {
        this.batteryMinutes = batteryMinutes;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getDocsisIsp() {
        return docsisIsp;
    }

    public void setDocsisIsp(String docsisIsp) {
        this.docsisIsp = docsisIsp;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getLastPoll() {
        return lastPoll;
    }

    public void setLastPoll(String lastPoll) {
        this.lastPoll = lastPoll;
    }

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getPoller() {
        return poller;
    }

    public void setPoller(String poller) {
        this.poller = poller;
    }

    public Boolean getSelfInstall() {
        return selfInstall;
    }

    public void setSelfInstall(Boolean selfInstall) {
        this.selfInstall = selfInstall;
    }

    public String getServiceCodes() {
        return serviceCodes;
    }

    public void setServiceCodes(String serviceCodes) {
        this.serviceCodes = serviceCodes;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSysDescr() {
        return sysDescr;
    }

    public void setSysDescr(String sysDescr) {
        sysDescr = StringEscapeUtils.escapeJava(sysDescr);
        this.sysDescr = sysDescr;
    }

    public String getSysLoc() {
        return sysLoc;
    }

    public void setSysLoc(String sysLoc) {
        this.sysLoc = sysLoc;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public ZonedDateTime getInsertDateTime() {
        return insertDateTime;
    }

    public void setInsertDateTime(ZonedDateTime insertDateTime) {
        this.insertDateTime = insertDateTime;
    }
}