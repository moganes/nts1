package com.spectrum.notes.notesDataConsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.notes.notesDataConsumer.model.SNMPMonMetricsFlat;
import com.spectrum.notes.notesDataConsumer.model.complexEntities.SNMPMonMetrics;
import com.spectrum.notes.notesDataConsumer.repository.SNMPMetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.spectrum.notes.notesDataConsumer.utils.Constants.SNMP_CONTAINER_ID;

/**
 * @author alam
 */
@Service
@Slf4j
public class SNMPMetircsReceiver {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SNMPMetricsRepository snmpMetricsRepository;
   // private final ExecutorService executor;


    @Autowired
    public SNMPMetircsReceiver(SNMPMetricsRepository snmpMetricsRepository//,
                               //ExecutorService executor
                               ) {
        this.snmpMetricsRepository = snmpMetricsRepository;
       // this.executor = executor;
    }

    @KafkaListener(id = SNMP_CONTAINER_ID,
            autoStartup = "false",
            idIsGroup = false,
            topics = {"#{'${app.topic.snmpMetrics}'.split(',')}"})
    public void listen(
            List<ConsumerRecord<String, String>> list
    ) throws IOException {

        log.info("received messages size='{}'", list.size());
       // SNMPDbThread snmpDbThread = new SNMPDbThread(snmpMetricsRepository, list);
       // executor.submit(snmpDbThread);


        if (list == null) {
            log.error("null ConsumerRecord list received {}", list);
        } else {

            try {

                List<SNMPMonMetricsFlat> snmpMonMetricsFlatList = list.parallelStream()
                        .map(record -> {

                            /*log.info("status = received for topic = {}, partition = {}, offset = {}, Timestamp = {}",
                                    record.topic(),
                                    record.partition(),
                                    record.offset(), record.timestamp());*/

                            SNMPMonMetrics snmpMonMetrics = null;
                            try {
                                snmpMonMetrics = mapper.readValue(record.value().toString(), SNMPMonMetrics.class);
                            } catch (IOException e) {
                                log.error("status = Error while consuming from topic = snmpmon.metrics" +
                                                " , this message is dropped moving to next message , record:{}" +
                                                "Exception: {}",
                                        record,
                                        e);
                            }
                            return snmpMonMetrics.toFlat();
                        })
                        .collect(Collectors.toList());


                long startTime = System.currentTimeMillis();
                snmpMetricsRepository.saveAll(snmpMonMetricsFlatList);
                log.info("TimeTaken = " + (System.currentTimeMillis() - startTime));

                /*
                Commented as trying to send less records to splunk
                list.forEach(record -> log.info("status = created successfully for topic = {}, partition = {}, offset = {}",
                        record.topic(),
                        record.partition(),
                        record.offset()));*/


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
