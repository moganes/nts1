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
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interface_edge")
public class InterfaceEdge {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "interface_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID interfaceEdgeSid;

    private String id;
    private String name;
    private String alias;
    private int type;
    private Boolean direction;
    private int frequency;
    private int width;
    private int mtu;
    private int speed;

    @Column(name = "physical_address")
    private String physicalAddress;

    private int idx;
    private int chid;
    private int mdidx;

    @Column(name = "sgidx_list", nullable = true)
    private String sgidxList;
    // TODO: this was a int list , converted to , delimeited string
    private String modulation;
    private int modprofile;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;


    public InterfaceEdge convertTo(Equipment.Interface grpcInterface) {


        return InterfaceEdge.builder()
                .id(grpcInterface.getId())
                .name(grpcInterface.getName())
                .alias(grpcInterface.getAlias())
                .type(grpcInterface.getType())
                .direction(grpcInterface.getDirection())
                .frequency(grpcInterface.getFrequency())
                .width(grpcInterface.getWidth())
                .mtu(grpcInterface.getMtu())
                .speed(grpcInterface.getSpeed())
                .physicalAddress(grpcInterface.getPhysaddress())
                .idx(grpcInterface.getIdx())
                .chid(grpcInterface.getChid())
                .mdidx(grpcInterface.getMdidx())
                .sgidxList(
                        (grpcInterface.getSgidxList() != null && grpcInterface.getSgidxList().size() > 0) ?
                                grpcInterface.getSgidxList()
                                        .stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(",")) : null)
                .modulation(grpcInterface.getModulation())
                .modprofile(grpcInterface.getModprofile())
                .build();
    }
}