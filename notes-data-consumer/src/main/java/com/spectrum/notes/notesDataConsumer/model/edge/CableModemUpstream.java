package com.spectrum.notes.notesDataConsumer.model.edge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cable_modem_upstream_edge")
public class CableModemUpstream {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cable_modem_upstream_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID cableModemUpstreamSid;

    @Column(name = "_key_")
    private int key;
    private String value;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}