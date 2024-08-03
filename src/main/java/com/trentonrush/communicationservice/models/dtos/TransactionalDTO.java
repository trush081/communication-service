package com.trentonrush.communicationservice.models.dtos;

import com.trentonrush.communicationservice.models.Message;

public class TransactionalDTO {
    private String source;
    private String messageType;
    private Message message;

    public TransactionalDTO() {
        // empty constructor
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
