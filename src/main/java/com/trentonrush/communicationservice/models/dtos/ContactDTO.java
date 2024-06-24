package com.trentonrush.communicationservice.models.dtos;

import com.trentonrush.communicationservice.models.Message;

public class ContactDTO {
    private String source;
    private Message message;

    public ContactDTO() {
        // empty constructor
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
