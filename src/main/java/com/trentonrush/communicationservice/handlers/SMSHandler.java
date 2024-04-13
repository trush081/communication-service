package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.enums.MessageType;
import org.springframework.stereotype.Service;

@Service
public class SMSHandler implements CommunicationHandler {
    @Override
    public MessageType getMessageType() {
        return MessageType.SMS;
    }
    @Override
    public void send(Communication communication) {
        // put twillo SMSService call here
    }
}
