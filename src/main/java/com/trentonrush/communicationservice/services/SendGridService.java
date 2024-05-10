package com.trentonrush.communicationservice.services;

import com.trentonrush.communicationservice.configs.SendGridConfig;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.sendgrid.SendGridPersonalization;
import com.trentonrush.communicationservice.models.sendgrid.SendGridRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * SendGrid Service
 */
@Service
public class SendGridService {

    private final SendGridConfig sendGridConfig;
    private final RestClient sendGridRestClient;

    SendGridService(SendGridConfig sendGridConfig, RestClient sendGridRestClient) {
        this.sendGridConfig = sendGridConfig;
        this.sendGridRestClient = sendGridRestClient;
    }

    /**
     * Send an email through SendGrid
     * @param message details of what is needed to send an email
     */
    public void sendEmail(Message message) {
        message.setResponseCode(sendGridRestClient
                .post()
                .body(new SendGridRequest.Builder()
                        .setFrom(message.getSender())
                        .setTemplateId(sendGridConfig.getSendGridTemplateId(message.getTemplate()))
                        .addPersonalization(new SendGridPersonalization.Builder()
                                .addTo(message.getRecipient())
                                .setDynamicTemplateData(message.getMessageDetails())
                                .build())
                        .build())
                .retrieve().toBodilessEntity().getStatusCode().value());
    }
}
