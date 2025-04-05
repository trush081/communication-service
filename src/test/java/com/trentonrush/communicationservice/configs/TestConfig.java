package com.trentonrush.communicationservice.configs;

import com.trentonrush.communicationservice.utils.TestConstants;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * I have chosen to only do a portion of the tests
 */
@TestConfiguration
@EnableConfigurationProperties(CommunicationProperties.class)
public class TestConfig {

    @Bean
    public CommunicationProperties communicationProperties() {
        CommunicationProperties properties = new CommunicationProperties();
        CommunicationProperties.SendGrid sendGrid = new CommunicationProperties.SendGrid();
        Map<String, String> senders = new HashMap<>();
        senders.put("trentonrush", TestConstants.TRENTONRUSH_TEST_EMAIL);
        sendGrid.setSenders(senders);
        properties.setSendgrid(sendGrid);
        return properties;
    }
}
