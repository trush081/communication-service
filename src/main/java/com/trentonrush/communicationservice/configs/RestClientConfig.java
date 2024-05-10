package com.trentonrush.communicationservice.configs;

import com.trentonrush.communicationservice.interceptors.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${communication.sendgrid.url}")
    String getSendGridUrl;

    @Value("${communication.sendgrid.api-key}")
    String sendGridApiKey;

    @Value("${communication.sendgrid.read-timeout}")
    int readTimeout;

    @Value("${communication.sendgrid.connect-timeout}")
    int connectTimeout;

    @Bean
    public RestClient sendGridRestClient() {
        return RestClient.builder()
                .baseUrl(getSendGridUrl)
                .requestInterceptor(new HttpLoggingInterceptor())
                .defaultHeader("Authorization", "Bearer " + sendGridApiKey)
                .build();
    }
}
