package com.spectrum.notesapi.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by "Mohammad Shah Alam" on 13 Oct, 2020
 */

@Configuration
public class AppConfig {

    @Bean
    public ExecutorService executorService(@Value("${app.executor-service.parallelism}") int parallelism) {
        return Executors.newWorkStealingPool(parallelism);
    }

    @Bean
    public RestTemplate spectrumSoRestTemplate(
            RestTemplateBuilder builder,
            @Value("${app.spectrumSo.ws.keystore.file-path}") String keystoreFilePath,
            @Value("${app.spectrumSo.ws.keystore.password}") String keystorePassword,
            @Value("${app.spectrumSo.ws.truststore.file-path}") String truststoreFilePath,
            @Value("${app.spectrumSo.ws.truststore.password}") String truststorePassword,

            @Value("${app.spectrumSo.ws.connection-timeout-ms}") int connectionTimeOut,
            @Value("${app.spectrumSo.ws.read-timeout-ms}") int readTimeOut) throws Exception {

        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(inStream2File(new ClassPathResource(keystoreFilePath), "notes-keystore-temp"), keystorePassword.toCharArray(), keystorePassword.toCharArray())
                .loadTrustMaterial(inStream2File(new ClassPathResource(truststoreFilePath), "so-truststore-temp"), truststorePassword.toCharArray())
                .build();

        HttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        factory.setConnectionRequestTimeout(connectionTimeOut);
        factory.setReadTimeout(readTimeOut);

        return new RestTemplate(factory);
    }

    private File inStream2File(Resource resource, String fName) {
        try {
            File tempFile = File.createTempFile("file", fName);
            Files.copy(resource.getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Problems loading Keystores", e);
        }
    }
}
