package com.spectrum.notes.notesDataConsumer.config;

import com.spectrum.notes.notesDataConsumer.deserializer.PollerDataDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alam
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${app.consumer.group-id}")
    private String groupId;

    @Value("${app.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${app.consumer.enable-auto-commit}")
    private String enableAutoCommit;

    @Value("${app.consumer.poll-timeout}")
    private int pollTimeout;

    @Value("${app.consumer.concurrency}")
    private int concurrency;

    @Value("${app.consumer.client-id}")
    private String clientID;

    @Value("${app.consumer.max-partition-fetch-bytes}")
    private int maxPartitionFetchBytes;

    @Value("${app.consumer.max-record-per-poll}")
    private int maxRecordPerPoll;


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PollerDataDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        System.out.println("$$$$$$$ Group id " + groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxRecordPerPoll);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientID);
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");

        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(pollTimeout);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        factory.setAutoStartup(false);
        factory.setBatchListener(true);
        return factory;
    }
}