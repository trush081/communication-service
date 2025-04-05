package com.trentonrush.communicationservice.configs;

import com.okta.commons.lang.Strings;
import com.trentonrush.communicationservice.models.enums.TwilioSmsTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

/**
 * Twilio Config
 */
@Configuration
@ConfigurationProperties(prefix = "communication.twilio.templates")
public class TwilioConfig {

    private String personal;

    private final Map<TwilioSmsTemplate, String> twilioTemplateMap = new EnumMap<>(TwilioSmsTemplate.class);

    @PostConstruct
    private void mapTwilioTemplate() {
        twilioTemplateMap.put(TwilioSmsTemplate.PERSONAL, personal);
    }

    public Map<TwilioSmsTemplate, String> getTwilioTemplateMap() {
        return twilioTemplateMap;
    }

    public String getTwilioTemplateId(String templateName) {
        String templateId = twilioTemplateMap.get(TwilioSmsTemplate.fromString(templateName));

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
