package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.enums.MessageType;
import org.springframework.stereotype.Service;

@Service
public interface CommunicationHandler {
    MessageType getMessageType();
    void send(Communication communication);
}
