package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.configs.CommunicationProperties;
import com.trentonrush.communicationservice.exceptions.InvalidInputException;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.LanguageDetectionService;
import com.trentonrush.communicationservice.services.SendGridService;
import com.trentonrush.communicationservice.utils.ValidationUtil;
import com.trentonrush.communicationservice.utils.CommunicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Email Handler
 */
@Service
public class EmailHandler implements CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmailHandler.class);

    private final Map<String, String> senders;
    private final Map<String, String> recipients;
    private final SendGridService sendGridService;
    private final LanguageDetectionService languageDetectionService;
    private final CommunicationRepository communicationRepository;

    public EmailHandler(CommunicationProperties communicationProperties,
                        SendGridService sendGridService,
                        LanguageDetectionService languageDetectionService,
                        CommunicationRepository communicationRepository) {
        this.senders = communicationProperties.getSendgrid().getSenders();
        this.recipients = communicationProperties.getSendgrid().getRecipients();
        this.sendGridService = sendGridService;
        this.languageDetectionService = languageDetectionService;
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
        ValidationUtil.validateEmail(communication.getMessage(), communication.getRequestType());

        // check contact specific email for inappropriate content
        if (CommunicationConstants.CONTACT.matches(communication.getRequestType())) {
            determineContactRecipient(communication.getMessage());
            languageDetectionService.checkLanguage(communication.getMessage().getMessageDetails());
        }

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
     * Safety net to direct contact email to predefined address
     * @param message details being sent
     */
    private void determineContactRecipient(Message message) {
        if (!recipients.containsKey(message.getRecipient())) {
            logger.warn("Contact recipient key {} not found", message.getRecipient());
            throw new InvalidInputException("Contact recipient key is invalid");
        }
        message.setRecipient(recipients.get(message.getRecipient()));
    }


    /**
     * Determine what sender will be used based on the source given
     * can be expanded to more than one sender
     * @param message details being sent
     * @param source where the communication was called
     */
    private void determineSender(Message message, String source) {
        if (source.equals(CommunicationConstants.TRENTON_RUSH)) {
            message.setSender(senders.get(CommunicationConstants.TRENTON_RUSH));
        } else {
            logger.warn("Unrecognized source: {}. Sending from default sender.", source);
            message.setSender(senders.get(CommunicationConstants.TRENTON_RUSH));
        }
    }
}
