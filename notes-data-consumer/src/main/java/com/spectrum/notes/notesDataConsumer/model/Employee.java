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
/*** @author alam
 *//*

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_sid", unique = true, nullable = false)
    private Long employee_sid;

    @Column(name = "eid")
    @JsonProperty("EID")
    private String EID;

    @Column(name = "kma_code")
    @JsonProperty("KMACode")
    private String KMACode;

    @Column(name = "kma_desc")
    @JsonProperty("KMADesc")
    private String KMADesc;

    @Column(name = "account_number")
    private String accountNumber;

    private String company;
    private String department;

    @Column(name = "department_number")
    private String departmentNumber;

    private String division;

    @Column(name = "divison_desc")
    private String divisionDesc;

    @Column(name = "employee_id")
    private String employeeID;

    private Float enabled;

    private String enterprise_id;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "first_name")
    private String firstName;

    private String id;

    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "_key_")
    private String key;

    @Column(name = "key_attr")
    private String keyAttr;

    @Column(name = "last_name")
    private String lastName;

    private String manager_id;
    private String scope;
    private String title;
    private String type;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "work_city")
    private String workCity;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "work_state")
    private String workState;

    @Column(name = "work_street")
    private String workStreet;

    @Column(name = "work_zip")
    private String workZip;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;
}*/
