package com.trentonrush.communicationservice.models.enums;

import com.trentonrush.communicationservice.configs.TwilioConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TwilioSmsTemplate {

    PERSONAL;

    private static final Logger logger = LoggerFactory.getLogger(TwilioConfig.class);

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }

    public static TwilioSmsTemplate fromString(String templateId) {
        if (null == templateId || templateId.isEmpty()) {
            return null;
        }
        try {
            return TwilioSmsTemplate.valueOf(templateId.replace('-', '_').toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to convert templateId {} to enum TwilioSmsTemplate", templateId);
            return null;
        }
    }
}
