package com.trentonrush.communicationservice.services;

import com.trentonrush.communicationservice.configs.CommunicationProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final CommunicationProperties communicationProperties;

    TwilioService(CommunicationProperties communicationProperties) {
        this.communicationProperties = communicationProperties;
        Twilio.init(communicationProperties.getTwilio().getAccountSid(),
                communicationProperties.getTwilio().getAuthToken());
    }

    public void sendSms(com.trentonrush.communicationservice.models.Message message) {
        Message.creator(
                createTwilioPhoneNumber(message.getRecipient()),
                this.communicationProperties.getTwilio().getMessagingServiceSid(),
                message.getTemplate()
        )
        .create();
    }

    private PhoneNumber createTwilioPhoneNumber(String rawNumber) {
        if (rawNumber == null || rawNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }

        // Remove non-digit characters
        String digits = rawNumber.replaceAll("\\D", "");

        // Validate US number
        if (digits.length() == 10) {
            // Assume it's missing country code
            digits = "1" + digits;
        } else if (digits.length() == 11 && digits.startsWith("1")) {
            return new PhoneNumber("+" + digits);
        } else {
            throw new IllegalArgumentException("Invalid US phone number: " + rawNumber);
        }

        return new PhoneNumber("+" + digits);
    }
}
