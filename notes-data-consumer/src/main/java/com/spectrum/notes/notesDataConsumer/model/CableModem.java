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
    @Column(name = "cable_modem_sid", unique = true, nullable = false)
    private UUID cableModemSid;

    private String enterprise_name;
    private String market_name;
    private String region_id;
    private String type;

    @Column(name = "entity_type")
    private String entityType;

    @Column(columnDefinition = "TEXT")
    private String downstream_id;

    private String bootVersion;
    private String region_name;

    @Column(name = "sys_actual")
    private String sysActual;

    private String cmts_id;
    private String cpe_id;
    private String enterprise_id;
    @Column(name = "_key_")
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

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}