package com.trentonrush.communicationservice.models.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public enum SendGridEmailTemplate {

    PERSONAL;

    private static final Logger logger = LoggerFactory.getLogger(SendGridEmailTemplate.class);

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }

    /**
     * Get a SendGridEmailTemplate Enum from a string
     * @param templateId string that correlates to Enum
     * @return SendGridEmailTemplate Enum
     */
    public static SendGridEmailTemplate fromString(String templateId) {
        if (null == templateId || templateId.isEmpty()) {
            return null;
        }
        try {
            return SendGridEmailTemplate.valueOf(templateId.replace("-", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to convert templateId {} to enum SendGridEmailTemplate", templateId);
            return null;
        }
    }
}
