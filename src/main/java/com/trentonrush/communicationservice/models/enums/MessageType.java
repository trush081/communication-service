package com.trentonrush.communicationservice.models.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MessageType {
    EMAIL,
    SMS,
    OTHER;

    private static final Logger logger = LoggerFactory.getLogger(MessageType.class);

    public static MessageType fromString(String messageType) {
        if (null == messageType || messageType.isEmpty()) {
            return null;
        }
        try {
            return MessageType.valueOf(messageType.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to convert messageType {} to enum MessageType", messageType);
            return null;
        }
    }
}
