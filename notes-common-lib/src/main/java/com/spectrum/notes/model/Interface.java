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
@Table(name = "interface")
public class Interface {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interface_sid", unique = true, nullable = false)
    private Long interface_sid;
*/

    @PrePersist
    private void prePersist() {
        interface_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "interface_sid", unique = true, nullable = false)
    private UUID interface_sid;

    @Column(name = "admin_status")
    private String adminStatus;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "channel_width")
    private String chanwidth;

    private String cmts_id;

    @Column(name = "custom_alias")
    private String customAlias;

    private Float enabled;

    private String enterprise_id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_type")
    private String entityType;

    private String frequency;
    private String id;

    @Column(name = "if_descr")
    private String ifDescr;

    @Column(name = "int_alias")
    private String intAlias;

    @Column(name = "int_descr")
    private String intDescr;

    @Column(name = "if_idx")
    private Integer ifIdx;

    @Column(name = "int_direction")
    private String intDirection;

    @Column(name = "int_speed")
    private String intSpeed;

    @Column(name = "int_type")
    private String intType;

    @Column(name = "ipv4_netblocks", columnDefinition = "TEXT")
    private String ipv4Netblocks;

    @Column(name = "ipv6_netblocks", columnDefinition = "TEXT")
    private String ipv6Netblocks;

    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "last_change")
    private String lastChange;

    private String macDomain_id;
    private String market_id;

    @Column(name = "md_index_num")
    private String mdIndexNum;

    @Column(name = "mod_profile")
    private String modprofile;

    private String modulation;

    @Column(columnDefinition = "TEXT", name = "mtu")
    private String mtu;

    private String name;

    @Column(name = "node_list", columnDefinition = "TEXT")
    private String nodeList;

    @Column(name = "operational_status")
    private String operStatus;

    @Column(name = "physical_address")
    private String physAddress;

    private String poller;
    private String scope;

    @Column(name = "service_group")
    private String serviceGroup;
    private Float speed;

    @Column(name = "tx_power")
    private String txPower;

    private String type;
    private String vlanId;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}