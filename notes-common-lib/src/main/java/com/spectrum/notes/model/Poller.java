package com.spectrum.notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poller")
public class Poller {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poller_sid", unique = true, nullable = false)
    private Long poller_sid;*/

    @PrePersist
    private void prePersist() {
        poller_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "poller_sid", unique = true, nullable = false)
    private UUID poller_sid;


    private Float enabled;
    private String enterprise_id;
    private String enterprise_name;

    @Column(name = "entity_type")
    private String entityType;

    private String fqdn;
    private String ip;
    private String ipv4;
    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    @JsonProperty("market_id")
    @Column(name = "market_id")
    private String marketId;

    @JsonProperty("market_name")
    @Column(name = "market_name")
    private String marketName;

    @Column(name = "mgt_area_id")
    @JsonProperty("mgtArea_id")
    private String mgtAreaId;

    @Column(name = "mgt_area_name")
    @JsonProperty("mgtArea_name")
    private String mgtAreaName;

    private String name;
    private String os;
    private String region_id;
    private String region_name;

    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}
