package com.spectrum.notes.notesDataConsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "enterprise")
public class Enterprise {
 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_sid", unique = true, nullable = false)
    private Long enterprise_sid;*/

    @PrePersist
    private void prePersist() {
        enterprise_sid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue
    @Column(name = "enterprise_sid", unique = true, nullable = false)
    private UUID enterprise_sid;

    @Column(name = "com_basic_video_sub")
    private String comBasicVideoSub;

    @Column(name = "com_HSD_sub")
    @JsonProperty("comHSDSub")
    private String comHSDSub;

    @Column(name = "com_voice_sub")
    private String comVoiceSub;

    @Column(name = "emtity_type")
    private String entityType;

    private String id;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    private String name;

    @Column(name = "res_basic_video_sub")
    private String resBasicVideoSub;

    @Column(name = "res_HSD_sub")
    @JsonProperty("resHSDSub")
    private String resHSDSub;

    @Column(name = "res_voice_sub")
    private String resVoiceSub;
    private String scope;
    private String site_id;
    private String type;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}