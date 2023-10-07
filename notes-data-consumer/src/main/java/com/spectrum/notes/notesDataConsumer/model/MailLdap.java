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
@Table(name = "mail_ldap")
public class MailLdap {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_ldap_sid", unique = true, nullable = false)
    private Long mail_ldap_sid;*/

    @PrePersist
    private void prePersist() {
        mail_ldap_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "mail_ldap_sid", unique = true, nullable = false)
    private UUID mail_ldap_sid;

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