package com.spectrum.notes.notesDataConsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import static com.spectrum.notes.notesDataConsumer.utils.Constants.*;

@Component
@Slf4j
class AppEventListeners {

    private KafkaListenerEndpointRegistry registry;
    private String snmpMetricsTopics;
    private String propertiesTopics;
    private String dirtyTopics;
    private String groupId;
    private String clientId;

    @Autowired
    public AppEventListeners(
            KafkaListenerEndpointRegistry registry,
            @Value("${app.topic.snmpMetrics}") String snmpMetricsTopics,
            @Value("${app.topic.properties}") String propertiesTopics,
            @Value("${app.topic.edgeData}") String dirtyTopics,
            @Value("${app.consumer.group-id}") String groupId,
            @Value("${app.consumer.client-id}") String clientId
    ) {
        this.registry = registry;
        this.snmpMetricsTopics = snmpMetricsTopics;
        this.propertiesTopics = propertiesTopics;
        this.dirtyTopics = dirtyTopics;
        this.groupId = groupId;
        this.clientId = clientId;
    }

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {

        if (snmpMetricsTopics != null
                && snmpMetricsTopics.trim().length() > 0
                && !registry.getListenerContainer(SNMP_CONTAINER_ID).isRunning()) {
            registry.getListenerContainer(SNMP_CONTAINER_ID).getContainerProperties().setGroupId(groupId);
            registry.getListenerContainer(SNMP_CONTAINER_ID).getContainerProperties().setClientId(clientId);
            registry.getListenerContainer(SNMP_CONTAINER_ID).getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            registry.getListenerContainer(SNMP_CONTAINER_ID).start();
        }
        if (propertiesTopics != null
                && propertiesTopics.trim().length() > 0
                && !registry.getListenerContainer(PROPERTIES_CONTAINER_ID).isRunning()) {
            this.registry.getListenerContainer(PROPERTIES_CONTAINER_ID).getContainerProperties().setGroupId(groupId);
            this.registry.getListenerContainer(PROPERTIES_CONTAINER_ID).getContainerProperties().setClientId(clientId);
            this.registry.getListenerContainer(PROPERTIES_CONTAINER_ID).getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            this.registry.getListenerContainer(PROPERTIES_CONTAINER_ID).start();

        }
        if (dirtyTopics != null
                && dirtyTopics.trim().length() > 0
                && !registry.getListenerContainer(DIRTY_CONTAINER_ID).isRunning()) {
            this.registry.getListenerContainer(DIRTY_CONTAINER_ID).getContainerProperties().setGroupId(groupId);
            this.registry.getListenerContainer(DIRTY_CONTAINER_ID).getContainerProperties().setClientId(clientId);
            this.registry.getListenerContainer(DIRTY_CONTAINER_ID).getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            this.registry.getListenerContainer(DIRTY_CONTAINER_ID).start();
        }
    }
}