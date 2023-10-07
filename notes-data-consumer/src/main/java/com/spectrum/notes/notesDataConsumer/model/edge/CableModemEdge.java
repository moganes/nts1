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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cable_modem_edge")
public class CableModemEdge {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cable_modem_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID cableModemEdgeSid;

    private String id;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "base_edge_sid")
    private Base base;

    private String docsisVersion;

    //Index within the CMTS
    private int idx;

    //How long in time ticks this CableModem has been up
    private int uptime;
    private int status;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "cable_modem_edge_sid")
    private List<CableModemComponent> componentList;
    private int sgidx;
    private int mdidx;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "cable_modem_edge_sid")
    private List<CableModemUpstream> upstreamList;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "cable_modem_edge_sid")
    private List<CableModemDownstream> downstreamList;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    public CableModemEdge convertTo(Equipment.CableModem grpcCableModem, String messageKey) {

        Equipment.Base grpcBase = grpcCableModem.getBase();

        return CableModemEdge.builder()
                .id(messageKey)
                .base(grpcBase != null ? Base.builder()
                        .id(grpcBase.getId())
                        .name(grpcBase.getName())
                        .ip(grpcBase.getIp())
                        .community(grpcBase.getCommunity())
                        .sysdescr(grpcBase.getSysdescr())
                        .serial(grpcBase.getSerial())
                        .make(grpcBase.getMake())
                        .model(grpcBase.getModel())
                        .physdescr(grpcBase.getPhysdescr())
                        .swversion(grpcBase.getSwversion())
                        .hwversion(grpcBase.getHwversion())
                        .lastpoll(grpcBase.getLastpoll())
                        .eqtype(grpcBase.getEqtype().name())
                        .build() : null)
                .docsisVersion(grpcCableModem.getDv().name())
                .idx(grpcCableModem.getIdx())
                .uptime(grpcCableModem.getUptime())
                .status(grpcCableModem.getStatus())
                .componentList(
                        (grpcCableModem.getComponentsCount() > 0) ?
                                grpcCableModem.getComponentsMap()
                                        .entrySet()
                                        .stream()
                                        .map(component -> CableModemComponent.builder()
                                                .key(component.getKey())
                                                .mac(component.getValue().getMac())
                                                .ip(component.getValue().getIp())
                                                .type(component.getValue().getTypeValue())
                                                .build())
                                        .collect(Collectors.toList())
                                      /*  .collect(toMap(
                                                componemt -> componemt.getKey(), componemt -> CableModemComponent.builder()
                                                        .ip(componemt.getValue().getIp())
                                                        .mac(componemt.getValue().getMac())
                                                        .componentType(componemt.getValue().getTypeValue())
                                                        // TODO: set the proper type string value
                                                        .build())
                                        )*/
                                : null
                )
                .sgidx(grpcCableModem.getSgidx())
                .mdidx(grpcCableModem.getMdidx())
                .upstreamList(grpcCableModem
                        .getUpstreamMap()
                        .entrySet()
                        .stream()
                        .map(x -> CableModemUpstream.builder()
                                .key(x.getKey())
                                .value(x.getValue())
                                .build())
                        .collect(Collectors.toList()))
                .downstreamList(grpcCableModem
                        .getDownstreamMap()
                        .entrySet()
                        .stream()
                        .map(x -> CableModemDownstream.builder()
                                .key(x.getKey())
                                .value(x.getValue())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}