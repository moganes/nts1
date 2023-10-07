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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "snmp_mon_metrics")
public class SNMPMonMetricsFlat {
    /*   @Id
       @GeneratedValue(strategy = GenerationType.TABLE)*/
/* @Id
 @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
 @SequenceGenerator(name = "auto_gen", sequenceName = "hibernate_sequence")*/
    /*@Id
    @GeneratedValue
    @Column(name = "snmp_mon_metrics_sid", unique = true, nullable = false)
    private Long snmpMonMetricsSid;*/

    @PrePersist
    private void prePersist() {
        snmp_mon_metrics_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    private UUID snmp_mon_metrics_sid;

    private Integer timestamp;
    private String metric;
    private Float value;
    private String id;

    @Column(name = "cmts_id")
    private String cmtsID;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}