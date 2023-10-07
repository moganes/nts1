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
@Table(name = "iqda_application")
public class IqdApplication {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iqda_application_sid", unique = true, nullable = false)
    private Long iqda_application_sid;
*/

    @PrePersist
    private void prePersist() {
        iqda_application_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "iqda_application_sid", unique = true, nullable = false)
    private UUID iqda_application_sid;

    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String module_id;

    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}