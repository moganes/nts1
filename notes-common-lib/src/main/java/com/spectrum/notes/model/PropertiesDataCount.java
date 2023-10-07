package com.spectrum.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties_data_count")
public class PropertiesDataCount {

    @PrePersist
    private void prePersist() {
        propertiesDataCountSid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "properties_data_count_sid", unique = true, nullable = false)
    private UUID propertiesDataCountSid;

    private String timestamp;
    private String topic;
    private Integer expectedCount;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

}