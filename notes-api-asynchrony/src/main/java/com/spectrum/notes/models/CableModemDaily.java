package com.spectrum.notes.models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//@Measurement(name = "CM_Daily", timeUnit = TimeUnit.NANOSECONDS)
public class CableModemDaily {
//    @Column(name = "ip_address")
    private String ipAddress;
//    @Column(name = "mac_address")
    private String macAddress;
//    @Column(name = "docsisVersion")
    private String docsisVersion;
//    @Column(name = "vendor")
    private String vendor;
//    @Column(name = "model")
    private String model;
//    @Column(name = "firmware")
    private String firmware;
//    @Column(name = "cmts_clli")
    private String cmtsClli;
//    @Column(name = "cmts_mac_address")
    private String cmtsMacAddress;
//    @Column(name = "cmts_ip_address")
    private String cmtsIpAddress;
//    @Column(name = "ip_address_hash", tag = true)
//    private String ipAddressHash;
//    @Column(name = "mac_address_hash", tag = true)
//    private String macAddressHash;
//    @Column(name = "cmts_clli_hash", tag = true)
//    private String cmtsClliHash;
//    @Column(name = "cmts_mac_address_hash", tag = true)
//    private String cmtsMacAddressHash;
//    @Column(name = "cmts_ip_address_hash", tag = true)
//    private String cmtsIpAddressHash;
}
