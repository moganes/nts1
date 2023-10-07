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
@Table(name = "base_edge")
public class Base {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "base_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID baseSid;

    private String id;
    private String name;
    private String ip;
    private String community;
    private String sysdescr;
    private String serial;
    private String make;
    private String model;
    private String physdescr;
    private String swversion;
    private String hwversion;
    private String eqtype;
    private int lastpoll;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}