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
@Table(name = "module")
public class Module {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_sid", unique = true, nullable = false)
    private Long module_sid;*/

    @PrePersist
    private void prePersist() {
        module_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "module_sid", unique = true, nullable = false)
    private UUID module_sid;

    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String id;
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}