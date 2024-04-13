package com.trentonrush.communicationservice.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.trentonrush.communicationservice.configs.SendGridConfig;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.enums.SendGridEmailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService {

    @Value("${communication.sendgrid.sendgridKey}")
    private String sendgridKey;

    private final SendGridConfig sendGridConfig;

    SendGridService(SendGridConfig sendGridConfig) {
        this.sendGridConfig = sendGridConfig;
    }

    public void sendEmail(Message message) {
        Email from = new com.sendgrid.helpers.mail.objects.Email(message.getSender());
        Email to = new com.sendgrid.helpers.mail.objects.Email(message.getRecipient());
        String subject = message.getSubject();
        Content content = new Content("text/html", message.getContent());
        Mail mail = new Mail(from, subject, to, content);
        mail.setTemplateId(sendGridConfig.getSendGridTemplateMap().get(
                SendGridEmailTemplate.fromString(message.getTemplate())
        ));

        SendGrid sendGrid = new SendGrid(sendgridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
