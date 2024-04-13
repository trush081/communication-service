package com.trentonrush.communicationservice.models.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public enum SendGridEmailTemplate {

    CUSTOM,
    TEST;

    private static final Logger logger = LoggerFactory.getLogger(SendGridEmailTemplate.class);

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }

    public static SendGridEmailTemplate fromString(String templateId) {
        if (null == templateId || templateId.isEmpty()) {
            return null;
        }
        try {
            return SendGridEmailTemplate.valueOf(templateId.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to convert templateId {} to a SendGridEmailTemplate", templateId);
            return null;
        }
    }
}
