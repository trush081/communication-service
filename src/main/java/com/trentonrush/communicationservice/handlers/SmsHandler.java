package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.configs.CommunicationProperties;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.TwilioService;
import com.trentonrush.communicationservice.utils.CommunicationConstants;
import com.trentonrush.communicationservice.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SMS Handler
 */
@Service
public class SmsHandler implements CommunicationHandler {

    private static final Logger logger = LoggerFactory.getLogger(SmsHandler.class);

    private final Map<String, String> senders;
    private final TwilioService twilioService;
    private final CommunicationRepository communicationRepository;

    public SmsHandler(CommunicationProperties communicationProperties,
                      TwilioService twilioService,
                      CommunicationRepository communicationRepository) {
        this.senders = communicationProperties.getTwilio().getSenders();
        this.twilioService = twilioService;
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
        determineSender(communication.getMessage(), communication.getSource());
        twilioService.sendSms(communication.getMessage());

        // Save updated communication details
        logger.info("Communication details updated: {}", communication);
        communicationRepository.save(communication);
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
