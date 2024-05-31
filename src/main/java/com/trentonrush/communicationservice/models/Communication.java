package com.trentonrush.communicationservice.models;

import com.trentonrush.communicationservice.models.dtos.CommunicationDTO;
import com.trentonrush.communicationservice.models.enums.MessageType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "communications")
public class Communication {
    @Id
    private String id;
    private String source;
    private MessageType messageType;
    private Message message;
    private LocalDateTime timestamp;

    public Communication() {
        this.timestamp = LocalDateTime.now();
    }

    public Communication(String source, MessageType messageType, Message message) {
        this.source = source;
        this.messageType = messageType;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Communication{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", messageType=" + messageType +
                ", message=" + message +
                ", timestamp=" + timestamp +
                '}';
    }

    public static Communication fromDTO(CommunicationDTO dto) {
        if (dto == null) return null;
        return new Communication(
                dto.getSource(),
                MessageType.fromString(dto.getMessageType()),
                dto.getMessage());
    }
}
