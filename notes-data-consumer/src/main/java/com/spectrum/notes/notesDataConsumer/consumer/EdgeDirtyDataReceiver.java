package com.spectrum.notes.notesDataConsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.notes.notesDataConsumer.model.edge.CMTSEdge;
import com.spectrum.notes.notesDataConsumer.model.edge.CableModemEdge;
import com.spectrum.notes.notesDataConsumer.model.edge.EquipmentEntityEdge;
import com.spectrum.notes.notesDataConsumer.model.edge.InterfaceEdge;
import com.spectrum.notes.notesDataConsumer.repository.CableModemEdgeRepository;
import com.spectrum.notes.notesDataConsumer.repository.CmtsEdgeRepository;
import com.spectrum.notes.notesDataConsumer.repository.EquipmentEntityEdgeRepository;
import com.spectrum.notes.notesDataConsumer.repository.InterfaceEdgeRepository;
import grpc.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.spectrum.notes.notesDataConsumer.utils.Constants.*;
import static java.util.stream.Collectors.toList;

/**
 * @author alam
 */
@Service
@Slf4j
public class EdgeDirtyDataReceiver {

    private final ObjectMapper mapper = new ObjectMapper();
    private final CableModemEdgeRepository cableModemEdgeRepository;
    private final InterfaceEdgeRepository interfaceEdgeRepository;
    private final CmtsEdgeRepository cmtsEdgeRepository;
    private final EquipmentEntityEdgeRepository equipmentEntityEdgeRepository;

    @Autowired
    public EdgeDirtyDataReceiver(CableModemEdgeRepository cableModemEdgeRepository,
                                 InterfaceEdgeRepository interfaceEdgeRepository,
                                 CmtsEdgeRepository cmtsEdgeRepository,
                                 EquipmentEntityEdgeRepository equipmentEntityEdgeRepository
    ) {
        this.cableModemEdgeRepository = cableModemEdgeRepository;
        this.interfaceEdgeRepository = interfaceEdgeRepository;
        this.cmtsEdgeRepository = cmtsEdgeRepository;
        this.equipmentEntityEdgeRepository = equipmentEntityEdgeRepository;
    }

    private boolean anyMatch(List<ConsumerRecord<String, byte[]>> list, String topicName) {
        return list.stream().anyMatch(c -> c.topic().equals(topicName));
    }

    @KafkaListener(id = DIRTY_CONTAINER_ID,
            autoStartup = "false",
            idIsGroup = false,
            topics = {"#{'${app.topic.edgeData}'.split(',')}"})
    public void listen(
            List<ConsumerRecord<String, byte[]>> list
    ) throws IOException {

        log.debug("received messages='{}'", list);

        if (list == null) {
            log.error("null ConsumerRecord list received {}", list);
        } else if (list.size() == 0) {
            log.error("Zero size ConsumerRecord list received {}", list);
        } else {

            try {
                long startTime = System.currentTimeMillis();

                list.stream()
                        .forEach(record -> log.info("status = received for topic = {}, partition = {}, offset = {}, Timestamp = {}",
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.timestamp()));

                // if (list.get(0).topic().equals(CABLEMODEM_EDGE_TOPIC)) {
                if (anyMatch(list, CABLEMODEM_EDGE_TOPIC)) {
                    List<CableModemEdge> cableModemEdgeList = list.stream()
                            .filter(r -> r.topic().equals(CABLEMODEM_EDGE_TOPIC))
                            .map(record -> {
                                CableModemEdge cableModemEdge = new CableModemEdge();
                                try {
                                    return cableModemEdge.convertTo(Equipment.CableModem.newBuilder().mergeFrom(((byte[]) record.value())).build(),
                                            record.key());
                                } catch (Exception e) {
                                    log.error("status = Error while consuming from topic = {}" +
                                                    " , this message is dropped moving to next message , record:{}" +
                                                    "Exception: {}",
                                            record.topic(),
                                            record,
                                            e);
                                    /*try {
                                        OutputStream os = new FileOutputStream(new File("data/errors/" + record.topic() + "-" + record.key().toString()));
                                        os.write(((byte[]) record.value()));
                                        os.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }*/
                                    return null;
                                }
                            })
                            .filter(cableModemEdge -> cableModemEdge != null)
                            .collect(toList());
                    cableModemEdgeRepository.saveAll(cableModemEdgeList);
                } else if (anyMatch(list, INTERFACE_EDGE_TOPIC)) {
                    List<InterfaceEdge> interfaceEdgeList = list.stream()
                            .filter(r -> r.topic().equals(INTERFACE_EDGE_TOPIC))
                            .map(record -> {
                                InterfaceEdge interfaceEdge = new InterfaceEdge();

                                try {
                                    return interfaceEdge.convertTo(Equipment.Interface.newBuilder().mergeFrom(((byte[]) record.value())).build());
                                } catch (Exception e) {
                                    log.error("status = Error while consuming from topic = {}" +
                                                    " , this message is dropped moving to next message , record:{}" +
                                                    "Exception: {}",
                                            record.topic(),
                                            record,
                                            e);
                                    /*try {
                                        OutputStream os = new FileOutputStream(new File("data/errors/" + record.topic() + "-" + record.key().toString()));
                                        os.write(((byte[]) record.value()));
                                        os.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }*/
                                    return null;
                                }
                            })
                            .filter(interfaceEdge -> interfaceEdge != null)
                            .collect(toList());
                    interfaceEdgeRepository.saveAll(interfaceEdgeList);
                } else if (anyMatch(list, CMTS_EDGE_TOPIC)) {
                    List<CMTSEdge> cmtsEdgeList = list.stream()
                            .filter(r -> r.topic().equals(CMTS_EDGE_TOPIC))
                            .map(record -> {
                                CMTSEdge cmtsEdge = new CMTSEdge();

                                try {
                                    return cmtsEdge.convertTo(Equipment.CMTS.newBuilder().mergeFrom(((byte[]) record.value())).build(),
                                            record.key());
                                } catch (Exception e) {
                                    log.error("status = Error while consuming from topic = {}" +
                                                    " , this message is dropped moving to next message , record:{}" +
                                                    "Exception: {}",
                                            record.topic(),
                                            record,
                                            e);
                                    /*try {
                                        OutputStream os = new FileOutputStream(new File("data/errors/" + record.topic() + "-" + record.key().toString()));
                                        os.write(((byte[]) record.value()));
                                        os.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }*/
                                    return null;
                                }
                            })
                            .filter(cmtsEdge -> cmtsEdge != null)
                            .collect(toList());
                    cmtsEdgeRepository.saveAll(cmtsEdgeList);
                } else if (
                        list.stream().anyMatch(c -> (
                                c.topic().equals(EQUIPMENT_EDGE_TOPIC) ||
                                        c.topic().equals(EQUIPMENT_BACKPLANE_EDGE_TOPIC) ||
                                        c.topic().equals(EQUIPMENT_CHASSIS_EDGE_TOPIC) ||
                                        c.topic().equals(EQUIPMENT_FAN_EDGE_TOPIC) ||
                                        c.topic().equals(EQUIPMENT_MODULE_EDGE_TOPIC) ||
                                        c.topic().equals(EQUIPMENT_POWERSUPPLY_EDGE_TOPIC)
                        ))) {
                    List<EquipmentEntityEdge> equipmentEntityEdgeList = list.stream()
                            .filter(c -> ( //TODO: duplicate
                                    c.topic().equals(EQUIPMENT_EDGE_TOPIC) ||
                                            c.topic().equals(EQUIPMENT_BACKPLANE_EDGE_TOPIC) ||
                                            c.topic().equals(EQUIPMENT_CHASSIS_EDGE_TOPIC) ||
                                            c.topic().equals(EQUIPMENT_FAN_EDGE_TOPIC) ||
                                            c.topic().equals(EQUIPMENT_MODULE_EDGE_TOPIC) ||
                                            c.topic().equals(EQUIPMENT_POWERSUPPLY_EDGE_TOPIC)
                            ))
                            .map(record -> {
                                EquipmentEntityEdge equipmentEntityEdge = new EquipmentEntityEdge();

                                try {

                                    return equipmentEntityEdge.convertTo(Equipment.Entity.newBuilder().mergeFrom(((byte[]) record.value())).build());
                                } catch (Exception e) {
                                    log.error("status = Error while consuming from topic = {}" +
                                                    " , this message is dropped moving to next message , record:{}" +
                                                    "Exception: {}",
                                            record.topic(),
                                            record,
                                            e);

                                    /*try {
                                        OutputStream os = new FileOutputStream(new File("data/errors/" + record.topic() + "-" + record.key().toString()));
                                        os.write(((byte[]) record.value()));
                                        os.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }*/
                                    return null;
                                }
                            })
                            .filter(equipmentEntityEdge -> equipmentEntityEdge != null)
                            .collect(toList());
                    equipmentEntityEdgeRepository.saveAll(equipmentEntityEdgeList);
                }

                log.info("TimeTaken = " + (System.currentTimeMillis() - startTime));

            } catch (Exception e) {

                list.forEach(record -> log.info("status = Error while consuming from topic = {}, partition = {}, offset = {}, exception: {} record = {}",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        e,
                        record));

            }
        }
    }
}