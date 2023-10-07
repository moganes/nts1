package com.spectrum.notes.notesDataConsumer.model.edge;

import grpc.Equipment;
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
@Table(name = "equipment_entity_edge")
public class EquipmentEntityEdge {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "equipment_entity_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID equipmentEntitySid;

    private String id;
    private String descr;

    @Column(name = "equipment_class")
    private String equipmentClass;

    private String name;
    private String make;
    private String model;
    private String serial;
    private String hwversion;
    private String fwversion;
    private String swversion;
    private Integer inside;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    public EquipmentEntityEdge convertTo(Equipment.Entity grpcEntity) {
        return EquipmentEntityEdge.builder()
                .id(grpcEntity.getId())
                .descr(grpcEntity.getDescr())
                .equipmentClass(grpcEntity.getEClass().name())
                .name(grpcEntity.getName())
                .make(grpcEntity.getMake())
                .model(grpcEntity.getModel())
                .serial(grpcEntity.getSerial())
                .hwversion(grpcEntity.getHwVersion())
                .fwversion(grpcEntity.getFwVersion())
                .swversion(grpcEntity.getSwVersion())
                .inside(grpcEntity.getInside())
                .build();
    }
}