/*
package com.spectrum.notes.notesDataConsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

*/
/**
 * @author alam
 *//*

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_sid", unique = true, nullable = false)
    private Long teamSid;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    private Double enabled;

    @Column(name = "entity_type")
    private String entityType;

    private String id;

    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "manager_id")
    @JsonProperty("manager_id")
    private String managerId;

    private String scope;

    @Column(name = "team_color")
    private String teamColor;

    @Column(name = "team_name")
    private String teamName;

    private String type;
}*/
