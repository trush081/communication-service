package com.trentonrush.communicationservice.configs;

import com.okta.commons.lang.Strings;
import com.trentonrush.communicationservice.models.enums.SendGridEmailTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "communication.sendgrid.templates")
public class SendGridConfig {

    private String personal;

    private Map<SendGridEmailTemplate, String> sendGridTemplateMap = new EnumMap<>(SendGridEmailTemplate.class);

    @PostConstruct
    public void mapSendGridTemplate(){
        sendGridTemplateMap.put(SendGridEmailTemplate.PERSONAL, personal);
    }

    public Map<SendGridEmailTemplate, String> getSendGridTemplateMap() {
        return sendGridTemplateMap;
    }

    public void setSendGridTemplateMap(Map<SendGridEmailTemplate, String> sendGridTemplateMap) {
        this.sendGridTemplateMap = sendGridTemplateMap;
    }

    public String getSendGridTemplateId(String templateName) {
        String templateId = sendGridTemplateMap.get(SendGridEmailTemplate.fromString(templateName));

        if (Strings.isEmpty(templateId)) {
            throw new IllegalArgumentException("Invalid templateName: " + templateName);
        }
        return templateId;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
