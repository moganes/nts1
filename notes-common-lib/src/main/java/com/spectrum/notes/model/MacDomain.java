package com.spectrum.notes.model;

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
@Table(name = "mac_domain")
public class MacDomain {

    @PrePersist
    private void prePersist() {
        mac_domain_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "mac_domain_sid", unique = true, nullable = false)
    private UUID mac_domain_sid;


    @Column(name = "admin_status")
    private Float adminStatus;

    @Column(name = "cm_udc_enabled")
    private Float cmUdcEnabled;

    private String cmts_id;

    @Column(name = "down_channel_annex")
    private Float downChannelAnnex;

    @Column(name = "early_auth_enct_ctrl")
    private Float earlyAuthEncrCtrl;

    private Float enabled;
    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String id;

    @Column(name = "if_alias")
    private String ifAlias;

    @Column(name = "if_descr")
    private String ifDescr;

    @Column(name = "if_type")
    private String iftype;

    @Column(name = "ip_porv_mode")
    private String ipProvMode;

    @Column(name = "ipv4_netblocks", columnDefinition = "TEXT")
    private String ipv4Netblocks;

    @Column(name = "ipv6_netblocks", columnDefinition = "TEXT")
    private String ipv6Netblocks;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private Float lastChange;

    @Column(name = "mcast_dsid_fwd_enabled")
    private Float mcastDsidFwdEnabled;

    @Column(name = "mdd_interval")
    private Float mddInterval;

    private String mgtArea_id;
    private Float mtu;

    @Column(name = "mult_tx_ch_mode_enabled")
    private Float multTxChModeEnabled;

    @Column(name = "mult_rx_ch_mode_enabled")
    private Float multRxChModeEnabled;

    private String name;

    @Column(name = "operational_status")
    private Float operStatus;

    @Column(name = "physical_address")
    private String physAddress;

    private String poller;
    private String scope;

    @Column(name = "send_udc_rules_enabled")
    private Float sendUdcRulesEnabled;

    @Column(name = "service_typeid_list")
    private Float serviceTypeIdList;

    @Column(name = "src_addr_verif_enabled")
    private Float srcAddrVerifEnabled;

    @Column(name = "status_ev_ctl_enabled")
    private Float statusEvCtlEnabled;

    @Column(name = "tftp_proxy_enabled")
    private Float tftpProxyEnabled;

    private String type;

    @Column(name = "us_freq_range")
    private Float usFreqRange;


    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}