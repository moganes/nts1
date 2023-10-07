package com.spectrum.notes.notesDataConsumer.model;

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
 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mac_domain_sid", unique = true, nullable = false)
    private Long mac_domain_sid;*/

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
    private Float lastChange;

    @Column(name = "mcast_dsid_fwd_enabled")
    private Float mcastDsidFwdEnabled;

    @Column(name = "mdd_interval")
    private Float mddInterval;

    private Float mtu;

    @Column(name = "mult_tx_ch_mode_enabled")
    private Float multTxChModeEnabled;

    @Column(name = "operational_status")
    private Float operStatus;

    @Column(name = "send_udc_rules_enabled")
    private Float sendUdcRulesEnabled;

    @Column(name = "src_addr_verif_enabled")
    private Float srcAddrVerifEnabled;

    @Column(name = "service_typeid_list")
    private Float serviceTypeIdList;

    @Column(name = "status_ev_ctl_enabled")
    private Float statusEvCtlEnabled;

    @Column(name = "tftp_proxy_enabled")
    private Float tftpProxyEnabled;

    @Column(name = "us_freq_range")
    private Float usFreqRange;

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

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String mgtArea_id;
    private String name;

    @Column(name = "physical_address")
    private String physAddress;

    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}