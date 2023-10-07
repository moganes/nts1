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
@Table(name = "cmts_edge")
public class CMTSEdge {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cmts_edge_sid", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    private UUID cmtsEdgeSid;

    private String id;

    @CreationTimestamp
    @Column(name = "insert_date_time")
    private ZonedDateTime insertDateTime;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "docsis_router_edge_sid")
    private DocsisRouter docsisRouter;

    public CMTSEdge convertTo(Equipment.CMTS grpcCmts, String key) {

        Equipment.DocsisRouter dr = grpcCmts.getDr();

        return CMTSEdge.builder()
                .id(key)
                .docsisRouter(dr != null ?
                        DocsisRouter.builder()
                                .base(dr.getBase() != null ?
                                        Base.builder()
                                                .id(dr.getBase().getId())
                                                .name(dr.getBase().getName())
                                                .ip(dr.getBase().getIp())
                                                .community(dr.getBase().getCommunity())
                                                .sysdescr(dr.getBase().getSysdescr())
                                                .serial(dr.getBase().getSerial())
                                                .make(dr.getBase().getMake())
                                                .model(dr.getBase().getModel())
                                                .physdescr(dr.getBase().getPhysdescr())
                                                .swversion(dr.getBase().getSwversion())
                                                .hwversion(dr.getBase().getHwversion())
                                                .eqtype(dr.getBase().getEqtype().name())
                                                .lastpoll(dr.getBase().getLastpoll())
                                                .build() :
                                        null)
                                .build()
                        : null
                )
                .build();
    }
}