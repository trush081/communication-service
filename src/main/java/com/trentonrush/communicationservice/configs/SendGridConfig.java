package com.trentonrush.communicationservice.configs;

import com.trentonrush.communicationservice.models.enums.SendGridEmailTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "communication.sendgrid.templates")
public class SendGridConfig {
    private String test;

    private Map<SendGridEmailTemplate, String> sendGridTemplateMap = new EnumMap<>(SendGridEmailTemplate.class);

    @PostConstruct
    public void mapSendGridTemplate(){
        sendGridTemplateMap.put(SendGridEmailTemplate.TEST, test);
    }

    public Map<SendGridEmailTemplate, String> getSendGridTemplateMap() {
        return sendGridTemplateMap;
    }

    public void setSendGridTemplateMap(Map<SendGridEmailTemplate, String> sendGridTemplateMap) {
        this.sendGridTemplateMap = sendGridTemplateMap;
    }
}
