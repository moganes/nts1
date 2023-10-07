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
@Table(name = "modem_ldap")
public class ModemLdap {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modem_ldap_sid", unique = true, nullable = false)
    private Long modem_ldap_sid;*/

    @PrePersist
    private void prePersist() {
        modem_ldap_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "modem_ldap_sid", unique = true, nullable = false)
    private UUID modem_ldap_sid;

    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String module_id;
    private String name;
    private String scope;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}