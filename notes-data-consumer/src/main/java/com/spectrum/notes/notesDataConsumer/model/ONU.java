package com.spectrum.notes.notesDataConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author alam
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "onu")
public class ONU {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onu_sid", unique = true, nullable = false)
    private Long onuSid;*/

    @PrePersist
    private void prePersist() {
        onuSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "onu_sid", unique = true, nullable = false)
    private UUID onuSid;

    private String account_id;
    private String account_name;
    private Boolean addressable;
    private String area_id;
    private String area_name;
    private String cpe_id;

    @Column(name = "cust_owned")
    private Boolean custOwned;

    private String division_id;
    private String division_name;
    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "equipment_type")
    private String equipmentType;

    private String hub_id;
    private String hub_name;
    private String id;

    @Column(name = "import_complete")
    private Boolean importComplete;

    @Column(name = "install_date")
    private String installDate;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String mac;
    private String make;
    private String market_id;
    private String market_name;
    private String mgtArea_id;
    private String mgtArea_name;
    private String model;
    private String name;
    private String node_id;
    private String node_name;
    private String port;
    private String region_id;
    private String region_name;
    private String scope;
    private String serial;
    private String sys;

    @Column(name = "sys_actual")
    private String sysActual;

    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}