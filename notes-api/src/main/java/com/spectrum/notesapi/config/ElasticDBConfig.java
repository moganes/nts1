package com.spectrum.notesapi.config;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.time.Duration;

/**
 * Created by "Mohammad Shah Alam" on 16 Oct, 2020
 */
@Configuration
@Getter
@Setter
public class ElasticDBConfig extends AbstractElasticsearchConfiguration {

    @Value("${app.elasticsearch.endpoint}")
    private String elasticEndpoint;

    @Value("${app.elasticsearch.connection-timeout-ms}")
    private int connectionTimeout;

    @Value("${app.elasticsearch.socket-timeout-ms}")
    private int socketTimeout;

    @Value("${app.elasticsearch.insertedTimeGreaterThan}")
    private String insertedTimeGreaterThan;

    @Value("${app.elasticsearch.insertedTimeLessThan}")
    private String insertedTimeLessThan;


    @Value("${app.elasticsearch.smb-insertedTimeGreaterThan}")
    private String smbInsertedTimeGreaterThan;

    @Value("${app.elasticsearch.smb-insertedTimeLessThan}")
    private String smbInsertedTimeLessThan;

    @Override
    @Bean(destroyMethod = "close")
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticEndpoint)
                .withConnectTimeout(Duration.ofMillis(connectionTimeout))
                .withSocketTimeout(Duration.ofMillis(socketTimeout))
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    public ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
