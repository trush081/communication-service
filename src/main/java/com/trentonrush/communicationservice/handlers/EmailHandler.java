package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.SendGridService;
import com.trentonrush.communicationservice.utils.ValidationUtil;
import com.trentonrush.communicationservice.utils.CommunicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Email Handler
 */
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

    /**
     * Get the Message Type for this handler
     * @return MessageType enum
     */
    @Override
    public MessageType getMessageType() {
        return MessageType.EMAIL;
    }

    /**
     * Sends the message details to third party service
     * @param communication request details being sent
     */
    @Override
    public void send(Communication communication) {
        // validate email message
        ValidationUtil.validateEmail(communication.getMessage());

        // Save initial communication
        communicationRepository.save(communication);

        // Send Communication Message
        determineSender(communication.getMessage(), communication.getSource());
        sendGridService.sendEmail(communication.getMessage());

        // Save updated communication details
        logger.info("Communication details updated: {}", communication);
        communicationRepository.save(communication);
    }

    /**
     * Set the specific sender domain to send a message from
     * @param message details being set
     * @param source where the communication was called
     */
    private void determineSender(Message message, String source) {
        switch (source) {
            case CommunicationConstants.TRENTON_RUSH, CommunicationConstants.GRANITE_SOLUTIONS -> message.setSender(trentonrush);
            case CommunicationConstants.UK_PRAY -> message.setSender(ukpray);
            default -> {
                logger.warn("Unrecognized source: {}. Sending from default sender.", source);
                message.setSender(trentonrush);
            }
        }
    }
}
