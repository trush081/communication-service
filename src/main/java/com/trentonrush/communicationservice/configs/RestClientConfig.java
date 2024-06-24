package com.trentonrush.communicationservice.configs;

import com.trentonrush.communicationservice.interceptors.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * Rest Client Config
 */
@Configuration
public class RestClientConfig {

    private final CommunicationProperties communicationProperties;

    public RestClientConfig(CommunicationProperties communicationProperties) {
        this.communicationProperties = communicationProperties;
    }

    @Bean
    public RestClient sendGridRestClient() {
        CommunicationProperties.SendGrid sendGridProps = communicationProperties.getSendgrid();
        return RestClient.builder()
                .baseUrl(sendGridProps.getUrl())
                .requestInterceptor(new HttpLoggingInterceptor())
                .defaultHeader("Authorization", "Bearer " + sendGridProps.getApiKey())
                .requestFactory(createClientHttpRequestFactory(sendGridProps.getConnectTimeout(), sendGridProps.getReadTimeout()))
                .build();
    }

    @Bean
    public RestClient openAiRestClient() {
        CommunicationProperties.OpenAi openAiProps = communicationProperties.getOpenAi();
        return RestClient.builder()
                .baseUrl(openAiProps.getUrl())
                .requestInterceptor(new HttpLoggingInterceptor())
                .defaultHeader("Authorization", "Bearer " + openAiProps.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .requestFactory(createClientHttpRequestFactory(openAiProps.getConnectTimeout(), openAiProps.getReadTimeout()))
                .build();
    }

    private ClientHttpRequestFactory createClientHttpRequestFactory(int connectTimeout, int readTimeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }
}
