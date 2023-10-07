package com.spectrum.notes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
//@Measurement(name = "CM_5", timeUnit = TimeUnit.NANOSECONDS)
public class CableModem5Min {

//    @Column(name = "admin_state")
    private String adminState;
//    @Column(name = "bandwidth_util")
    private Double bandwidthUtil;
//    @Column(name = "bps_data")
    private String bpsData;
//    @Column(name = "channels")
    private String channels;
//    @Column(name = "downstream_power")
    private Double downstreamPower;
//    @Column(name = "downstream_ccer")
    private Double downstreamCcer;
//    @Column(name = "downstream_cer")
    private Double downstreamCer;
//    @Column(name = "downstream_snr")
    private Double downstreamSnr;
//    @Column(name = "downstream_util")
    private Double downstreamUtil;
//    @Column(name = "last_poll")
    private Long lastPoll;
//    @Column(name = "LDAP_data")
    private String ldapData;
//    @Column(name = "link_speed")
    private Long linkSpeed;
//    @Column(name = "link_state")
    private String linkState;
//    @Column(name = "nextgen_data")
    private String nextgenData;
//    @Column(name = "ofdm")
    private String ofdm;
//    @Column(name = "on_hook_status")
    private String onHookStatus;
//    @Column(name = "oper_status")
    private String operStatus;
//    @Column(name = "operating_speed_list")
    private String operatingSpeedList;
//    @Column(name = "power")
    private Double power;
//    @Column(name = "service_class_name")
    private String serviceClassName;
//    @Column(name = "state")
    private String state;
//    @Column(name = "t3_t4_timeouts")
    private Long t3T4Timeouts;
//    @Column(name = "throughput")
    private Long throughput;
//    @Column(name = "last_cmts_registry_time")
    private Long lastCmtsRegistryTime;
//    @Column(name = "tuner_density")
    private String tunerDensity;
//    @Column(name = "upstream_ccer")
    private Double upstreamCcer;
//    @Column(name = "upstream_cer")
    private Double upstreamCer;
//    @Column(name = "upstream_power")
    private Double upstreamPower;
//    @Column(name = "upstream_snr")
    private Double upstreamSnr;
//    @Column(name = "upstream_util")
    private Double upstreamUtil;
//    @Column(name = "uptime")
    private Long uptime;
//    @Column(name = "util")
    private Double util;
//    @Column(name = "voice_enabled")
    private Boolean voiceEnabled;
//    @Column(name = "mac_address")
    private String macAddress;
//    @Column(name = "cmts_clli")
    private String cmtsClli;
//    @Column(name = "cmts_mac_address")
    private String cmtsMacAddress;
//    @Column(name = "cmts_ip_address")
    private String cmtsIpAddress;
//    @Column(name = "cmts_clli_hash", tag = true)
    private String cmtsClliHash;
//    @Column(name = "cmts_mac_address_hash", tag = true)
    private String cmtsMacAddressHash;
//    @Column(name = "cmts_ip_address_hash", tag = true)
    private String cmtsIpAddressHash;
//    @Column(name = "mac_address_hash", tag = true)
    private String macAddressHash;

}
