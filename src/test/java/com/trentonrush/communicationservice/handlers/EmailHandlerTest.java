package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.configs.CommunicationProperties;
import com.trentonrush.communicationservice.configs.TestConfig;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.MessageDetails;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.LanguageDetectionService;
import com.trentonrush.communicationservice.services.SendGridService;
import com.trentonrush.communicationservice.utils.CommunicationConstants;
import com.trentonrush.communicationservice.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailHandlerTest {

    private SendGridService sendGridService;
    private CommunicationProperties communicationProperties;
    private LanguageDetectionService languageDetectionService;
    private CommunicationRepository communicationRepository;
    private EmailHandler emailHandler;


    @BeforeEach
    public void setup() {
        sendGridService = mock(SendGridService.class);
        communicationRepository = mock(CommunicationRepository.class);
        languageDetectionService = mock(LanguageDetectionService.class);
        communicationProperties = new TestConfig().communicationProperties();
        emailHandler = new EmailHandler(communicationProperties, sendGridService, languageDetectionService, communicationRepository);
    }

    @Test
    void getMessageType() {
        assertEquals(MessageType.EMAIL, emailHandler.getMessageType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"trentonrush", "granitesolutions", "ukpray", "unknown"})
    void send_TransactionalEmailSources(String source) {
        Communication communication = buildCommunication(source, MessageType.EMAIL, CommunicationConstants.TRANSACTIONAL);
        doNothing().when(sendGridService).sendEmail(any(Message.class));
        doReturn(communication).when(communicationRepository).save(any(Communication.class));

        emailHandler.send(communication);
        assertEquals(Objects.equals(source, CommunicationConstants.UK_PRAY) ? TestConstants.UKPRAY_TEST_EMAIL : TestConstants.TRENTONRUSH_TEST_EMAIL, communication.getMessage().getSender());
        verify(communicationRepository, times(2)).save(communication);
        verify(sendGridService, times(1)).sendEmail(any(Message.class));
    }

    public static Communication buildCommunication(String source, MessageType messageType, String requestType) {
        Communication communication = new Communication();
        communication.setSource(source);
        communication.setMessageType(messageType);
        communication.setMessage(buildMessage());
        communication.setRequestType(requestType);
        return communication;
    }

    public static Message buildMessage() {
        Message message = new Message();
        message.setRecipient("email@example.com");
        message.setTemplate("test");
        message.setMessageDetails(new MessageDetails());
        return message;
    }
}
