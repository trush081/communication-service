package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.SendGridService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailHandler implements CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailHandler.class);

    @Value("${communication.sendgrid.senders.trentonrush}")
    private String trentonrush;

    @Value("${communication.sendgrid.senders.ukpray}")
    private String ukpray;

    private final SendGridService sendGridService;
    private final CommunicationRepository communicationRepository;

    public EmailHandler(SendGridService sendGridService, CommunicationRepository communicationRepository) {
        this.sendGridService = sendGridService;
        this.communicationRepository = communicationRepository;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.EMAIL;
    }

    @Override
    public void send(Communication communication) {
        //Save initial communication
        communicationRepository.save(communication);
        determineSender(communication.getMessage(), communication.getSource());
        sendGridService.sendEmail(communication.getMessage());

    }

    private void determineSender(Message message, String source) {
        switch (source) {
            case "trentonrush", "granitesolutions" -> message.setSender(trentonrush);
            case "ukpray" -> message.setSender(ukpray);
            default -> {
                logger.warn("Unrecognized source: {}. Sending from default sender.", source);
                message.setSender(trentonrush);
            }
        }
    }
}
