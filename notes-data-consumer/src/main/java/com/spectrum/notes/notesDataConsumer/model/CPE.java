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
@Table(name = "cpe")
public class CPE {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpe_sid", unique = true, nullable = false)
    private Long cpe_sid;*/

    @PrePersist
    private void prePersist() {
        cpe_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "cpe_sid", unique = true, nullable = false)
    private UUID cpe_sid;

    private String enterprise_name;
    private String model;
    private String scope;
    private String sys;

    @Column(name = "import_complete")
    private Boolean importComplete;

    private String hub_id;
    private String region_id;

    @Column(name = "cust_owned")
    private String custOwned;
    private String type;

    @Column(name = "entity_type")
    private String entityType;
    private String node_id;
    private String id;
    private String region_name;

    @Column(name = "sys_actual")
    private String sysActual;

    @Column(name = "install_date")
    private String installDate;

    private String name;
    private String hub_name;
    private String enterprise_id;
    @Column(name = "_key_")
    private String key;
    private Float enabled;

    @Column(name = "equipment_type")
    private String equipmentType;
    private String account_id;

    @Column(name = "key_attr")
    private String keyAttr;

    private Boolean lob_phone;
    private String mgtArea_name;

    @Column(name = "entity_name")
    private String entityName;
    private String account_name;
    private Boolean lob_data;
    private String node_name;
    private String division_id;
    private String serial;
    private Boolean lob_video;
    private String make;
    private String mgtArea_id;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}