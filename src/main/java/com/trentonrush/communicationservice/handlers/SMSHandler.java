package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * SMS Handler
 */
@Service
public class SMSHandler implements CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(SMSHandler.class);

    private final CommunicationRepository communicationRepository;

    public SMSHandler(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    /**
     * Get the Message Type for this handler
     * @return MessageType enum
     */
    @Override
    public MessageType getMessageType() {
        return MessageType.SMS;
    }

    /**
     * Sends triggers sending of an SMS specific message
     * @param communication details that specify message to be sent
     */
    @Override
    public void send(Communication communication) {
        // validate sms message
        ValidationUtil.validateSMS(communication.getMessage());

        // Save initial communication
        communicationRepository.save(communication);

        // Send Communication Message
        // TODO determine where to put phone number sanitization

        // Save updated communication details
        logger.info("Communication details updated: {}", communication);
        communicationRepository.save(communication);
    }
}
